package com.digitalriver.worldpayments.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Supplies basic crypto operations
 *
 */
public class CryptoUtils {

    public static class CryptoException extends Exception {

        private static final long serialVersionUID = 1L;

        public CryptoException(String aMessage) {
            super(aMessage);
        }

        public CryptoException(String aMessage, Throwable aCause) {
            super(aMessage, aCause);
        }
    }

    public static final String ASYMMETRIC_CIPHER = "RSA/ECB/PKCS1Padding";

    private static final SecureRandom cRandom = new SecureRandom();

    /**
     * Initialization vector, must be shared by modules that encrypts and
     * decrypts data
     */
    private static final byte[] INIT_VECTOR_128_BIT = new byte[] { -1, 2, -3,
        4, -5, 6, -7, 8, -9, 10, -11, 12, -13, 14, -15, 16 };

    public static final String SYMMETRIC_CIPHER = "AES/CBC/PKCS5Padding";

    /**
     * Creates a new AES key
     *
     * @param aSize
     *            key length
     * @return an AES key
     * @throws CryptoException if crypto fails
     */
    public static SecretKey createKey(int aSize) throws CryptoException {
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance("AES");
            kg.init(aSize, cRandom);
        } catch (Exception e) {
            throw new CryptoException("Failed to init key generator!", e);
        }
        return kg.generateKey();
    }

    /**
     * Signs a message
     *
     * @param aPrivateKey
     *            the key to sign with
     * @param aMessage
     *            the message to sign
     * @return the signature
     * @throws CryptoException
     *             on any error
     */
    public static byte[] createSignature(PrivateKey aPrivateKey, byte[] aMessage)
            throws CryptoException {
        Signature sig;
        try {
            sig = Signature.getInstance("SHA1withRSA");

            sig.initSign(aPrivateKey);
        } catch (Exception e) {
            throw new CryptoException("Failed to set up signature!", e);
        }

        try {
            sig.update(aMessage);

            return sig.sign();
        } catch (Exception e) {
            throw new CryptoException("Failed to create signature!", e);
        }
    }

    /**
     * Decrypts data with a asymmetric key
     *
     * @param aKey
     *            the key to use
     * @param aCipherText
     *            the encrypted data
     * @return decrypted data
     * @throws CryptoException
     *             on any error
     */
    public static byte[] decryptAsymmetric(Key aKey, byte[] aCipherText)
            throws CryptoException {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(ASYMMETRIC_CIPHER);

            cipher.init(Cipher.DECRYPT_MODE, aKey);
        } catch (Exception e) {
            throw new CryptoException("Failed to set up cipher", e);
        }

        try {
            return cipher.doFinal(aCipherText);
        } catch (Exception e) {
            throw new CryptoException("Failed to decrypt data!", e);
        }
    }

    /**
     * Decrypts data with a symmetric key
     *
     * @param aKey
     *            the key to use
     * @param aCipherText
     *            the encrypted data
     * @return decrypted data
     * @throws CryptoException
     *             on any error
     */
    public static byte[] decryptSymmetric(SecretKey aKey, byte[] aCipherText)
            throws CryptoException {
        Cipher symCipher;
        try {
            symCipher = Cipher.getInstance(SYMMETRIC_CIPHER);

            symCipher.init(Cipher.DECRYPT_MODE, aKey, new IvParameterSpec(
                    INIT_VECTOR_128_BIT));
        } catch (Exception e) {
            throw new CryptoException("Failed to set up cipher!", e);
        }

        try {
            return symCipher.doFinal(aCipherText);
        } catch (Exception e) {
            throw new CryptoException("Failed to decrypt data!", e);
        }
    }

    /**
     * Encrypts data with a asymmetric key
     *
     * @param aKey
     *            the key to use
     * @param aPlainText
     *            the data to encrypt
     * @return encrypted data
     * @throws CryptoException
     *             on any error
     */
    public static byte[] encryptAsymmetric(Key aKey, byte[] aPlainText)
            throws CryptoException {
        Cipher asymCipher;
        try {
            asymCipher = Cipher.getInstance(ASYMMETRIC_CIPHER);
            asymCipher.init(Cipher.ENCRYPT_MODE, aKey);
        } catch (Exception e) {
            throw new CryptoException("Failed to set up cipher!", e);
        }
        try {
            return asymCipher.doFinal(aPlainText);
        } catch (Exception e) {
            throw new CryptoException("Failed to encrypt data!", e);
        }
    }

    /**
     * Encrypts data with a symmetric key
     *
     * @param aKey
     *            the key to use
     * @param aPlainText
     *            the data to encrypt
     * @return encrypted data
     * @throws CryptoException
     *             on any error
     */
    public static byte[] encryptSymmetric(SecretKey aKey, byte[] aPlainText)
            throws CryptoException {
        Cipher symCipher;
        try {
            symCipher = Cipher.getInstance(SYMMETRIC_CIPHER);

            symCipher.init(Cipher.ENCRYPT_MODE, aKey, new IvParameterSpec(
                    INIT_VECTOR_128_BIT));
        } catch (Exception e) {
            throw new CryptoException("Failed to set up cipher!", e);
        }

        try {
            return symCipher.doFinal(aPlainText);
        } catch (Exception e) {
            throw new CryptoException("Failed to encrypt data!", e);
        }
    }

    public static byte[] modulusToMd5(BigInteger modulus)
            throws CryptoException {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(modulus.toByteArray());
            return algorithm.digest();
        } catch (NoSuchAlgorithmException nse) {
            throw new CryptoException("No such algoritm MD5");
        }
    }

    /**
     * Restores an AES key from its byte representation
     *
     * @param aKey
     *            the key data
     * @return an AES key
     */
    public static SecretKey restoreKey(byte[] aKey) {
        return new SecretKeySpec(aKey, "AES");
    }

    /**
     * Unzips data zipped with GZIP
     *
     * @param aSource
     *            zipped data
     * @return unzipped data
     * @throws CryptoException
     *             on any error
     */
    public static byte[] unzip(byte[] aSource) throws CryptoException {
        try {
            int read;
            byte[] tmp = new byte[aSource.length];

            ByteArrayOutputStream baos = new ByteArrayOutputStream(
                    aSource.length);
            GZIPInputStream gzis = new GZIPInputStream(
                    new ByteArrayInputStream(aSource));

            while (true) {
                read = gzis.read(tmp, 0, tmp.length);

                if (read <= 0) {
                    break;
                }
                baos.write(tmp, 0, read);
            }

            return baos.toByteArray();
        } catch (IOException e) {
            throw new CryptoException("Failed to unzip data!", e);
        }
    }

    /**
     * Verifies a signature
     *
     * @param aKey
     *            the public key corresponding to the private key used to sign
     *            the message
     * @param aMessage
     *            the signed message
     * @param aSignature
     *            the signature
     * @return true if the signature matches, false otherwise
     * @throws CryptoException
     *             on any error
     */
    public static boolean verifySignature(PublicKey aKey, byte[] aMessage,
            byte[] aSignature) throws CryptoException {
        Signature sig;
        try {
            sig = Signature.getInstance("SHA1withRSA");
            sig.initVerify(aKey);
        } catch (Exception e) {
            throw new CryptoException("Failed to set up signature!", e);
        }
        try {
            sig.update(aMessage);
            return sig.verify(aSignature);
        } catch (Exception e) {
            throw new CryptoException("Failed to verify signature!", e);
        }
    }

    /**
     * Zips data using GZIP
     *
     * @param aSource
     *            data to zip
     * @return zipped data
     * @throws CryptoException
     *             on any error
     */
    public static byte[] zip(byte[] aSource) throws CryptoException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
            GZIPOutputStream gzos = new GZIPOutputStream(baos);

            gzos.write(aSource);

            gzos.close();

            return baos.toByteArray();
        } catch (IOException e) {
            // Should never happen
            throw new CryptoException("Failed to zip data!", e);
        }
    }

}
