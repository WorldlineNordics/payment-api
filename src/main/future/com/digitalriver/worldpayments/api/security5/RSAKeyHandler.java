package com.digitalriver.worldpayments.api.security5;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.digitalriver.worldpayments.api.utils.CryptoUtils;
import com.digitalriver.worldpayments.api.utils.CryptoUtils.CryptoException;

public class RSAKeyHandler implements KeyHandler {

    private static String byte2hex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
    private RSAPrivateKey privateKey;

    private byte[] privateKeyFingerprint;

    private RSAPublicKey publicKey;
    private byte[] publicKeyFingerprint;

    // Fingerprint, public key
    private final Map<String, RSAPublicKey> publicKeys = new HashMap<String, RSAPublicKey>();

    public final RSAPrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public final RSAPrivateKey getPrivateKey(byte[] fingerprint) {
        if (!Arrays.equals(fingerprint, this.privateKeyFingerprint)) {
            throw new IllegalArgumentException(
                    "Missing private key with fingerprint "
                    + byte2hex(fingerprint));
        }
        return getPrivateKey();
    }

    public final byte[] getPrivateKeyFingerprint() {
        return this.privateKeyFingerprint;
    }

    public final RSAPublicKey getPublicKey() {
        return this.publicKey;
    }

    /**
     * Get's a public key given a fingerprint
     */
    public final RSAPublicKey getPublicKey(byte[] fingerprint) {
        RSAPublicKey pubKey = this.publicKeys.get(byte2hex(fingerprint));
        if (pubKey == null) {
            throw new IllegalArgumentException(
                    "Missing public key with fingerprint "
                    + byte2hex(fingerprint));
        }
        return pubKey;
    }

    public byte[] getPublicKeyFingerprint() {
        return this.publicKeyFingerprint;
    }

    protected void setKeys(RSAPrivateKey privateKey, RSAPublicKey... publicKeys)
    throws CryptoException {
        this.privateKey = privateKey;
        this.privateKeyFingerprint = CryptoUtils.modulusToMd5(privateKey
                .getModulus());

        this.publicKey = publicKeys[0];
        this.publicKeyFingerprint = CryptoUtils.modulusToMd5(publicKeys[0].getModulus());

        for (RSAPublicKey pubKey : publicKeys) {
            this.publicKeys.put(
                    byte2hex(CryptoUtils.modulusToMd5(pubKey.getModulus())),
                    pubKey);
        }
    }

}
