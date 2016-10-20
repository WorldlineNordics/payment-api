package com.digitalriver.worldpayments.api.security5;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class DERKeyHandler extends RSAKeyHandler {

    public static String DEFAULT_DRWP_PUB_KEY_FILE = "drwp_pub.der";
    public static String DEFAULT_MERCHANT_DER_FILE = "merchant_key.der";

    private static final RSAPrivateKey readPrivateDER(final File privateKeyDERFile)
    throws NoSuchAlgorithmException, IOException,
    InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        byte[] keyBytes = slurp(privateKeyDERFile);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return (RSAPrivateKey) kf.generatePrivate(spec);
    }

    private static final RSAPublicKey readPublicDER(final File publicKeyDERFile)
    throws NoSuchAlgorithmException, IOException,
    InvalidKeySpecException {

        KeyFactory kf = KeyFactory.getInstance("RSA");
        byte[] keyBytes = slurp(publicKeyDERFile);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    private static final byte[] slurp(File file) throws IOException {
        FileInputStream fis = new FileInputStream( file );
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) file.length()];
        dis.readFully(keyBytes);
        dis.close();        
        return keyBytes;
    }

    public DERKeyHandler() {
        this( new File(DERKeyHandler.class.getResource("/"+DEFAULT_MERCHANT_DER_FILE).getFile()) );
    }

    public DERKeyHandler(File privateKeyDERFile) {
        this(privateKeyDERFile,
                new File(DERKeyHandler.class.getResource("/"+DEFAULT_DRWP_PUB_KEY_FILE).getFile()) );
    }

    public DERKeyHandler(File privateKeyDERFile, File publicKeyDERFile) {
        try {
            setKeys(readPrivateDER(privateKeyDERFile),
                    readPublicDER(publicKeyDERFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
