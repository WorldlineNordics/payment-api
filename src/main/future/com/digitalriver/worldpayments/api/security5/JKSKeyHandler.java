package com.digitalriver.worldpayments.api.security5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;

public class JKSKeyHandler extends RSAKeyHandler {

	private static final KeyStore getKeystore( String keyStoreName, String keyStorePass ) {
        KeyStore keyStore;

        try {
            keyStore = KeyStore.getInstance("JKS");
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Could not instantiate keystore! Cause: "
                    + e.getMessage());
        }
        try {
            keyStore.load(getKeyStoreInputStream(keyStoreName), keyStorePass.toCharArray());
            return keyStore;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Could not access keystore: " + keyStoreName + ". Cause: "
                    + e.getMessage());
        }
    }

    private static final InputStream getKeyStoreInputStream(String keyStoreName) {
        InputStream keyStoreStream ;
        try {
            keyStoreStream = new FileInputStream(new File(keyStoreName));
        } catch (FileNotFoundException e1) {
            keyStoreStream = ClassLoader
            .getSystemResourceAsStream(keyStoreName);

            if (keyStoreStream == null) {
                throw new IllegalArgumentException(
                        "Could not locate KeyStore: " + keyStoreName);
            }
        }
        return keyStoreStream;
    }

    private static final RSAPrivateKey getPrivateKey(KeyStore keyStore, String aKeyName, 
            String keyStorePass) {
        try {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();

                if (alias.startsWith(aKeyName)) {
                    return (RSAPrivateKey) keyStore.getKey(alias,
                            keyStorePass.toCharArray());
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not access key "
                    + aKeyName + " from keystore. Cause: " + e.getMessage());
        }
    }

    private static final RSAPublicKey getPublicKey(KeyStore keyStore, String aCertName, 
            String keyStorePass) {
        try {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();

                if (alias.startsWith(aCertName))
                {
                    X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

                    if (cert == null)
                    {
                        continue;
                    }
                    
                    return (RSAPublicKey)cert.getPublicKey();
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not access certificate "
                    + aCertName + " from keystore. Cause: " + e.getMessage());
        }
    }

    /**
	 * 
	 * @param aKeyStore
	 * @param aKeyStorePwd
	 * @param aKeyName
	 * @param aCertName typically ngcert
	 * @throws NoSuchAlgorithmException 
	 */
    public JKSKeyHandler(String aKeyStore, String aKeyStorePwd,
            String aKeyName, String aCertName) {

        KeyStore keyStore = getKeystore(aKeyStore, aKeyStorePwd);

        RSAPrivateKey priv = getPrivateKey( keyStore, aKeyName, aKeyStorePwd );

        RSAPublicKey pub = getPublicKey( keyStore, aCertName, aKeyStorePwd );

        try {
            setKeys(priv, pub);
		} catch (Exception e) {
            throw new IllegalArgumentException("Could not load jks keystore", e );
		} 
        
    }
}
