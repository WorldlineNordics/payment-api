package com.digitalriver.worldpayments.api.security6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class JKSKeyHandlerV6 {
		
	private static byte[] longTo4Bytes(Long aLong) {
		long val = aLong.longValue();

		if (val < 0) {
			throw new IllegalArgumentException("Negative value");
		}
		if (val > Integer.MAX_VALUE * 2L) {
			throw new IllegalArgumentException("Value to large!");
		}

		return new byte[] { (byte) (val >> 24 & 0xFF),
				(byte) ((val >> 16) & 0xFF), (byte) (val >> 8 & 0xFF),
				(byte) (val & 0xFF) };
	}

	private KeyPair iMerchantKeyPair;

	private Map<Long, KeyPair> iMerchantKeyPairMap;

	private byte[] iMerchantKeyPairSerialNo;

	private PublicKey iDrwpPublicKey;

	private Map<Long, PublicKey> iDrwpPublicKeyMap;

	private byte[] iDrwpPublicKeySerialNo;

	/**
	 * A KeyHandler that reads private keys and public certificates from a Java
	 * KeyStore
	 * 
	 * @param aKeyStore
	 *            path to the Java KeyStore to use
	 * @param aKeyStorePwd
	 *            password of the KeyStore
	 * @param aMerchantPrivateKeyName
	 *            name of the merchant's private key
	 * @param drwpCertificateName
	 *            name of the DRWP certificate
	 */
	public JKSKeyHandlerV6(String aKeyStore, String aKeyStorePwd,
			String aMerchantPrivateKeyName, String drwpCertificateName) {
		iMerchantKeyPairMap = new Hashtable<Long, KeyPair>();
		iDrwpPublicKeyMap = new Hashtable<Long, PublicKey>();
		KeyStore ks = loadKeyStore(aKeyStore, aKeyStorePwd);
		init(ks, aMerchantPrivateKeyName, aKeyStorePwd, drwpCertificateName);
	}
	

	/**
	 * Gets the keystore's most recent merchant public/private key pair. 
	 * @return
	 */
	public KeyPair getMerchantKeyPair() {
		return iMerchantKeyPair;
	}

	/**
	 * Gets the merchant public/private key pair from the kesytore for a specific certificate serial number.
	 * @param aSerialNo The serial number for the merchant's certificate.
	 * @return
	 */
	public KeyPair getMerchantKeyPair(long aSerialNo) {
		return iMerchantKeyPairMap.get(new Long(aSerialNo));
	}

	/**
	 * Gets the serial number for the keystore's most recent merchant certificate.
	 * @return
	 */
	public byte[] getMerchantKeyPairSerialNo() {
		return iMerchantKeyPairSerialNo;
	}

	/**
	 * Get's the public key from the most recent DRWP certificate in the keystore. 
	 * @return
	 */
	public PublicKey getDrwpPublicKey() {
		return iDrwpPublicKey;
	}

	/**
	 * Gets the public key from a DRWP certificate for a specific certificate serial number.
	 * @param aSerialNo The serial number for the DRWP certificate.
	 * @return
	 */
	public PublicKey getDrwpPublicKey(long aSerialNo) {
		return iDrwpPublicKeyMap.get(new Long(aSerialNo));
	}

	/**
	 * Gets the serial number for the keystore's most recent DRWP certificate.
	 * @return
	 */
	public byte[] getDrwpPublicKeySerialNo() {
		return iDrwpPublicKeySerialNo;
	}

	private void init(KeyStore aKeyStore, String aKeyName, String aKeyPwd,
			String drwpCertificateName) {
		try {
			Enumeration<String> aliases = aKeyStore.aliases();
			Long tempSerialNo = new Long(Long.MAX_VALUE);
			Date newest = new Date(0);

			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();

				if (alias.startsWith(aKeyName)) {
					PrivateKey merchantPrivateKey = (PrivateKey) aKeyStore.getKey(alias,
							aKeyPwd.toCharArray());
					X509Certificate merchant_cert = (X509Certificate) aKeyStore
							.getCertificate(alias);

					if (merchantPrivateKey == null || merchant_cert == null) {
						continue;
					}
					Long serialNo = new Long(merchant_cert.getSerialNumber().longValue());
					iMerchantKeyPairMap.put(serialNo, new KeyPair(merchant_cert.getPublicKey(),
							merchantPrivateKey));

					// Check if the certificate is most recent
					if (merchant_cert.getNotBefore().after(newest)) {
						newest = merchant_cert.getNotBefore();
						tempSerialNo = serialNo;
					}
				}
			}

			if (tempSerialNo.longValue() == Long.MAX_VALUE) {
				throw new NullPointerException(
						"No matching keypair found in keystore!");
			}

			// Set the most recent private/public key pair as THE key pair.
			iMerchantKeyPair = iMerchantKeyPairMap.get(tempSerialNo);
			iMerchantKeyPairSerialNo = longTo4Bytes(tempSerialNo);
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not access key "
					+ aKeyName + " from keystore. Cause: " + e.getMessage());
		}

		try {
			Enumeration<String> aliases = aKeyStore.aliases();
			Long tempSerialNo = new Long(Long.MAX_VALUE);
			Date newest = new Date(0);

			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();

				if (alias.startsWith(drwpCertificateName)) {
					X509Certificate drwpCert = (X509Certificate) aKeyStore
							.getCertificate(alias);

					if (drwpCert == null) {
						continue;
					}
					Long serialNo = new Long(drwpCert.getSerialNumber().longValue());
					iDrwpPublicKeyMap.put(serialNo, drwpCert.getPublicKey());

					// Check if the certificate is most recent
					if (drwpCert.getNotBefore().after(newest)) {
						newest = drwpCert.getNotBefore();
						tempSerialNo = serialNo;
					}
				}
			}
			if (tempSerialNo.longValue() == Long.MAX_VALUE) {
				throw new NullPointerException(
						"No matching trusted certificate found in keystore!");
			}
			iDrwpPublicKey = iDrwpPublicKeyMap.get(tempSerialNo);
			iDrwpPublicKeySerialNo = longTo4Bytes(tempSerialNo);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Could not access DRWP certificate: "
							+ drwpCertificateName
							+ " from keystore. Cause: " + e.getMessage());
		}
	}

	private KeyStore loadKeyStore(String aKeyStore, String aKeyStorePwd) {
		KeyStore keyStore;
		InputStream keyStoreStream = null;

		try {
			keyStoreStream = new FileInputStream(new File(aKeyStore));
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(keyStoreStream, aKeyStorePwd.toCharArray());
		} catch (FileNotFoundException e1) {
			keyStoreStream = ClassLoader.getSystemResourceAsStream(aKeyStore);
			try {
				keyStore = KeyStore.getInstance("JKS");
				keyStore.load(keyStoreStream, aKeyStorePwd.toCharArray());
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"Could not create keystore: " + aKeyStore + ". Cause: "
								+ e.getMessage());
			}
			if (keyStoreStream == null) {
				throw new IllegalArgumentException(
						"Could not locate KeyStore: " + aKeyStore);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not create keystore: "
					+ aKeyStore + ". Cause: " + e.getMessage());
		} finally {
			try {
				if (keyStoreStream != null)
					keyStoreStream.close();
			} catch (IOException e) {
				// Do nothing.
			}
		}
		return keyStore;
	}

}
