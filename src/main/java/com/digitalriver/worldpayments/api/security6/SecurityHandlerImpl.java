package com.digitalriver.worldpayments.api.security6;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import com.digitalriver.worldpayments.api.security.SecurityHandler;
import com.digitalriver.worldpayments.api.security.SecurityHandlerException;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;
import com.digitalriver.worldpayments.api.utils.Base64Utils;
import com.digitalriver.worldpayments.api.utils.CryptoUtils;
import com.digitalriver.worldpayments.api.utils.CryptoUtils.CryptoException;

/**
 * SecurityHandlerImpl must be only used when CSR keysize is 2048 bit. 
 *
 */
public class SecurityHandlerImpl implements SecurityHandler {

	private static final String ENCODING_UTF_8 = "UTF-8";

	public static final byte RSA_2048_AES_128_ENC_MODE_V6 = 6;

	private Base64Utils iBase64Encoder;

	private JKSKeyHandlerV6 iKeyHandler;

	public SecurityHandlerImpl(JKSKeyHandlerV6 aKeyHandler) {
		iKeyHandler = aKeyHandler;
		iBase64Encoder = new Base64Utils();
	}

	/* (non-Javadoc)
	 * @see com.digitalriver.worldpayments.api.securityv6.SecurityHandlerI#decrypt(java.lang.String)
	 */
	public String decrypt(String aRedirect) {
		byte[] encryptedKey = new byte[256];
		byte[] signature = new byte[256];
		byte[] cipherText;
		byte[] envelope;
		KeyPair merchantPrivPubKeyPair;
		PublicKey drwpPublicKey;
		System.out.println("Contentttttttttttt"+aRedirect);
		envelope = iBase64Encoder.decode(aRedirect);
		

		switch (envelope[0]) {
		case RSA_2048_AES_128_ENC_MODE_V6:
			if (envelope.length < 522) {
				throw new SecurityHandlerException(
						"Invalid envelope: Too short! Len=" + envelope.length);
			}

			/*
			 * Chunks: [1byte]+[8bytes]+[8bytes]+[256bytes]+[128bytes]+[1..n
			 * bytes]
			 */
			/*
			 * Content:
			 * <type>+<serial#>+<serial#>+<key>+<signature>+<encrypted_data>
			 */
			cipherText = new byte[envelope.length - 521];

			byte[] receiverSN = new byte[4];
			byte[] senderSN = new byte[4];

			System.arraycopy(envelope, 1, receiverSN, 0, 4);
			System.arraycopy(envelope, 5, senderSN, 0, 4);
			System.arraycopy(envelope, 9, encryptedKey, 0, 256);
			System.arraycopy(envelope, 265, signature, 0, 256);
			System.arraycopy(envelope, 521, cipherText, 0, cipherText.length);

			merchantPrivPubKeyPair = iKeyHandler.getMerchantKeyPair(new BigInteger(receiverSN)
					.longValue());
			drwpPublicKey = iKeyHandler.getDrwpPublicKey(new BigInteger(senderSN)
					.longValue());
			break;
		default:
			throw new SecurityHandlerException(
					"Invalid envelope! Unknown mode: " + envelope[0]);
		}

		if (merchantPrivPubKeyPair == null) {
			throw new SecurityHandlerException(
					"Could not retrieve merchant key pair!");
		}
		if (drwpPublicKey == null) {
			throw new SecurityHandlerException(
					"Could not retrieve DRWP key!");
		}

		byte[] unencryptedKey;
		byte[] plainText;
		SecretKey secretKey;

		try {
			unencryptedKey = CryptoUtils.decryptAsymmetric(merchantPrivPubKeyPair.getPrivate(), encryptedKey);
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to retrieve encrypted key!", e);
		}

		secretKey = CryptoUtils.restoreKey(unencryptedKey);

		try {
			plainText = CryptoUtils.decryptSymmetric(secretKey, cipherText);
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to decrypt envelope content!", e);
		}

			try {
				plainText = CryptoUtils.unzip(plainText);
			} catch (CryptoException e) {
				throw new SecurityHandlerException(
						"Failed to unzip plaintext!", e);
			}

		try {
			if (!CryptoUtils.verifySignature(drwpPublicKey, cipherText, signature)) {
				throw new SecurityHandlerException("Signature does not match!");
			}
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to verify signature!", e);
		}

		try {
			return new String(plainText, ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// Should never happen
			throw new SecurityHandlerException(
					"Failed to convert result to UTF-8", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.digitalriver.worldpayments.api.securityv6.SecurityHandlerI#encrypt(java.lang.String)
	 */
	public String encrypt(String aRedirect) throws SecurityHandlerException {
		byte[] plainText;
		byte[] cipherText;
		byte[] signature;
		byte[] encryptedSharedKey;
		byte[] merchantCertificateSerialNo;
		byte[] drwpPublicKeySerialNo;
		SecretKey sharedSecretkey;
		PublicKey drwpPublicKey;

		// Fetch DRWP key and serial numbers
		drwpPublicKey = iKeyHandler.getDrwpPublicKey();
		drwpPublicKeySerialNo = iKeyHandler.getDrwpPublicKeySerialNo();
		merchantCertificateSerialNo = iKeyHandler.getMerchantKeyPairSerialNo();

		try {
			plainText = aRedirect.getBytes(ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// Should never happen
			throw new SecurityHandlerException(
					"Could not extract redirect as UTF-8", e);
		}

		try {
			// Create message key
			sharedSecretkey = CryptoUtils.createKey(128);
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to create secret key", e);
		}

		// Zip content
		try {
			plainText = CryptoUtils.zip(plainText);
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to zip content data!", e);
		}

		try {
			// Encrypt message, i.e. redirect content
			cipherText = CryptoUtils.encryptSymmetric(sharedSecretkey, plainText);
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to encrypt redirect data!", e);
		}

		try {
			// Create a signature of the encrypted data
			signature = CryptoUtils.createSignature(iKeyHandler.getMerchantKeyPair()
					.getPrivate(), cipherText);

			// verify length
			if (signature.length != 256) {
				throw new SecurityHandlerException(
						"Invalid signature length! Len=" + signature.length);
			}
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to create signature!", e);
		}

		try {
			// Encrypt the shared secret key with the DRWP public key
			encryptedSharedKey = CryptoUtils.encryptAsymmetric(drwpPublicKey,
					sharedSecretkey.getEncoded());

			// verify length
			if (encryptedSharedKey.length != 256) {
				throw new SecurityHandlerException(
						"Invalid encrypted key length! Len="
								+ encryptedSharedKey.length);
			}
		} catch (CryptoException e) {
			throw new SecurityHandlerException(
					"Failed to encrypt message key!", e);
		}

		int startpos = 1;

		byte[] result = new byte[1 + 8 + encryptedSharedKey.length + signature.length
				+ cipherText.length];

		// Assign mode
		result[0] = RSA_2048_AES_128_ENC_MODE_V6;

		// Pack data
		System.arraycopy(drwpPublicKeySerialNo, 0, result, startpos, 4);
		startpos += 4;

		System.arraycopy(merchantCertificateSerialNo, 0, result, startpos, 4);
		startpos += 4;

		System.arraycopy(encryptedSharedKey, 0, result, startpos, encryptedSharedKey.length);
		startpos += encryptedSharedKey.length;

		System.arraycopy(signature, 0, result, startpos, signature.length);
		startpos += signature.length;

		System.arraycopy(cipherText, 0, result, startpos, cipherText.length);

		return iBase64Encoder.encode(result);
	}

}
