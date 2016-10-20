package com.digitalriver.worldpayments.api.security5;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import com.digitalriver.worldpayments.api.security.SecurityHandler;
import com.digitalriver.worldpayments.api.security.SecurityHandlerException;
import com.digitalriver.worldpayments.api.utils.Base64Utils;
import com.digitalriver.worldpayments.api.utils.CryptoUtils;
import com.digitalriver.worldpayments.api.utils.CryptoUtils.CryptoException;

/**
 * Packs and extracts encrypted data using a PKCS#7-like envelope.
 * 
 * This code is a derived and simplified version of the SecurityHandlerImpl in
 * PaymentPageServer. The implementations must be synchronized! A security
 * handler encrypts and signs sensitive data so that it can be transmitted over
 * an unsecure medium. It also unpacks secured data and verifies its signature.
 */
public class SecurityHandlerImpl implements SecurityHandler {

	private static final String ENCODING_UTF_8 = "UTF-8";

	private static final byte RSA_1024_AES_128_ENC_MODE = 5;

	private Base64Utils iEncoder;

	private KeyHandler iKeyHandler;

	public SecurityHandlerImpl(KeyHandler aKeyHandler) {
		iKeyHandler = aKeyHandler;
		iEncoder = new Base64Utils();
	}

	/**
	 * Unpacks a redirect string from a PKCS#7-like envelope
	 * 
	 * @param encryptedEnvelope
	 *            the protected envelope
	 * @return a decrypted and verified message
	 * @throws SecurityHandlerException
	 *             on any internal errors such as corrupt content or certificate
	 *             mismatches
	 */
	public String decrypt(String encryptedEnvelope) {

		byte[] signKeyMd5 = new byte[16];
		byte[] encKeyMd5 = new byte[16];
		byte[] encKey = new byte[128];
		byte[] signature = new byte[128];
		byte[] cipherText;
		byte[] envelope;
		PrivateKey decryptKey;
		PublicKey verifySignKey;

		envelope = iEncoder.decode(encryptedEnvelope);

		/*
		 * Chunks:
		 * [1byte]+[16bytes]+[16bytes]+[8bytes]+[128bytes]+[128bytes]+[1..n
		 * bytes]
		 */
		/*
		 * Content:
		 * <type>+<fingerprint_sign_key#>+<fingerprint_enc_key#>+<key>+<
		 * signature>+<encrypted_data>
		 */

		if (envelope[0] == RSA_1024_AES_128_ENC_MODE) {
			if (envelope.length < 289) {
				throw new SecurityHandlerException(
						"Invalid envelope: Too short! Len=" + envelope.length);
			}
		} else {
			throw new SecurityHandlerException(
					"Invalid envelope! Unknown mode: " + envelope[0]);
		}

		cipherText = new byte[envelope.length - 289];

		System.arraycopy(envelope, 1, signKeyMd5, 0, 16);
		System.arraycopy(envelope, 17, encKeyMd5, 0, 16);
		System.arraycopy(envelope, 33, encKey, 0, 128);
		System.arraycopy(envelope, 161, signature, 0, 128);
		System.arraycopy(envelope, 289, cipherText, 0, cipherText.length);

		verifySignKey = iKeyHandler.getPublicKey(signKeyMd5);
		decryptKey = iKeyHandler.getPrivateKey(encKeyMd5);

		byte[] key;
		byte[] plain;
		SecretKey secretKey;

		try {
			key = CryptoUtils.decryptAsymmetric(decryptKey, encKey);
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to retrieve encrypted key!", e);
		}

		secretKey = CryptoUtils.restoreKey(key);

		try {
			plain = CryptoUtils.decryptSymmetric(secretKey, cipherText);
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to decrypt envelope content!", e);
		}

		try {
			plain = CryptoUtils.unzip(plain);
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to unzip plaintext!", e);
		}

		try {
			if (!CryptoUtils.verifySignature(verifySignKey, cipherText,
					signature)) {
				throw new SecurityHandlerException("Signature does not match!");
			}
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to verify signature!", e);
		}

		try {
			return new String(plain, ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new SecurityHandlerException(
					"Failed to convert result to UTF-8", e);
		}
	}

	/**
	 * Secures a redirect string in a PKCS#7-like envelope
	 * 
	 * @param plainText
	 *            the string to protect
	 * @return an encrypted and signed envelope
	 * @throws SecurityHandlerException
	 *             on any internal errors such as corrupt content or missing key
	 *             data
	 */
	public String encrypt(String plainText) throws SecurityHandlerException {

		byte[] plain;
		byte[] cipherText;
		byte[] signature;
		byte[] encryptedKey;

		SecretKey key;

		try {
			plain = plainText.getBytes(ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// Should never happen
			throw new SecurityHandlerException(
					"Could not extract redirect as UTF-8", e);
		}

		try {
			// Create message key
			key = CryptoUtils.createKey(128);
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to create secret key", e);
		}

		// Zip content
		try {
			plain = CryptoUtils.zip(plain);
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to zip content data!", e);
		}

		try {
			// Encrypt message, i.e. redirect content
			cipherText = CryptoUtils.encryptSymmetric(key, plain);
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to encrypt redirect data!", e);
		}

		try {
			// Create a signature of the encrypted data
			signature = CryptoUtils.createSignature(
					iKeyHandler.getPrivateKey(), cipherText);

			// verify length
			if (signature.length != 128) {
				throw new SecurityHandlerException(
						"Invalid signature length! Len=" + signature.length);
			}
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to create signature!", e);
		}

		try {
			// Encrypt message key with public key
			encryptedKey = CryptoUtils.encryptAsymmetric(
					iKeyHandler.getPublicKey(), key.getEncoded());

			// verify length
			if (encryptedKey.length != 128) {
				throw new SecurityHandlerException(
						"Invalid encrypted key length! Len="
								+ encryptedKey.length);
			}
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to encrypt message key!", e);
		}

		int startpos = 1;
		byte[] result = new byte[1 + 16 + 16 + encryptedKey.length
				+ signature.length + cipherText.length];

		/*
		 * Chunks:
		 * [1byte]+[16bytes]+[16bytes]+[8bytes]+[128bytes]+[128bytes]+[1..n
		 * bytes]
		 */
		/*
		 * Content:
		 * <type>+<fingerprint_sign_key#>+<fingerprint_enc_key#>+<key>+<
		 * signature>+<encrypted_data>
		 */

		// Assign mode
		result[0] = RSA_1024_AES_128_ENC_MODE;

		// Pack data
		System.arraycopy(iKeyHandler.getPrivateKeyFingerprint(), 0, result,
				startpos, 16);
		startpos += 16;
		System.arraycopy(iKeyHandler.getPublicKeyFingerprint(), 0, result,
				startpos, 16);
		startpos += 16;
		System.arraycopy(encryptedKey, 0, result, startpos, encryptedKey.length);
		startpos += encryptedKey.length;
		System.arraycopy(signature, 0, result, startpos, signature.length);
		startpos += signature.length;
		System.arraycopy(cipherText, 0, result, startpos, cipherText.length);

		return iEncoder.encode(result);
	}

}
