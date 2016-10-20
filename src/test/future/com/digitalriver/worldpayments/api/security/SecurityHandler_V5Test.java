package com.digitalriver.worldpayments.api.security;

import java.io.File;

import org.junit.Assert;

import org.junit.Test;

import com.digitalriver.worldpayments.api.security5.DERKeyHandler;
import com.digitalriver.worldpayments.api.security5.JKSKeyHandler;
import com.digitalriver.worldpayments.api.security5.SecurityHandlerImpl;

public class SecurityHandler_V5Test {

    @Test
    public void testDer() {
        File merchantPrivateFile = new File(SecurityHandler_V5Test.class.getResource("/merchant_key.der").getFile());
        File merchantPublicFile = new File(SecurityHandler_V5Test.class.getResource("/merchant_pub.der").getFile());
        File drwpPubFile = new File(SecurityHandler_V5Test.class.getResource("/drwp_pub.der").getFile());
        File drwpPrivateFile = new File(SecurityHandler_V5Test.class.getResource("/drwp_key.der").getFile());

        SecurityHandlerImpl encryptor = new SecurityHandlerImpl(
                new DERKeyHandler(merchantPrivateFile, drwpPubFile));

        String DECRYPTED = "A=123456789;C=123;E=OrderId12345;F=2011-03-31 14:04:15 CEST;T=true";
        String ENCRYPTED = encryptor.encrypt(DECRYPTED);

        //String ENCRYPTED = "BeStMi9r9bvvmu6dA4GBKu39PdI1LzsSOpI5WM8qFkazZJlk6cWeUIqjR59pbfEvg80ZahTjA1E3WioxvF1N0czcZ65N5RpaY0iLxs7nBqeyxdlL3HRJgAfWugKXbCTc-ru8Z6PrySYYU23b29nLeQuPZdp-EniIzHCpQSHxdNm7Ae5Shc-pw6aA5PUaKpN6ObQdLZ8C0-JpGe6LnjCQ781cDZSnzW87mGfxcfa-U4VG8t2L6M87QG6m5rS-CyqO_FkkuXqvK4NgQ_1x_CPc1BzmLFkygOOpI9XQAHiKmlOS-8jw5-vir77OEGqr9TaFKePfdvTxQ0xLxjXCZhTU0dKZNm5OtpQs1Byoh18kFtwTE_bQwfLNYgCBYBZAOWsVVuCDnS-4KkH3LtRzXK6rZ88QrDMi9UZRFBjhdmq-vdQNg4aMCJiNn_jYcV2Prlf1l-JKd3_8zLscY_WZhT4NMIyyrh7AhzSUyxgvdGagBwdynR6CYmfb8c5lQ7oLvMMoYTvjquObpTdqIM_ezTMQHdU=";

        SecurityHandlerImpl decryptor = new SecurityHandlerImpl(new DERKeyHandler(
                drwpPrivateFile, merchantPublicFile ));

        String decr = decryptor.decrypt(ENCRYPTED);

        Assert.assertEquals(DECRYPTED, decr);
    }

    @Test
    public void testJks() {
        SecurityHandlerImpl encryptor = new SecurityHandlerImpl(
                new JKSKeyHandler( "src/test/resources/testkeysV5/drwp.jks", "123456", "netgiro_1", "dummy"));

        String DECRYPTED = "A=123456789;C=123;E=OrderId12345;F=2011-03-31 14:04:15 CEST;T=true";
        String ENCRYPTED = encryptor.encrypt(DECRYPTED);
        System.out.println(ENCRYPTED);

        //BXkEqvoghwFXE0M5JKr1XooBifJiuOLL3Gz7yhvq5juzOryyEWHFjmk_oD21wBhW0T3_lz4RJAWv0fSBMy0USJKLHYAgzzHfoaVz8UI1-9ktcuElTXNoRZEPajF5618JO2CO1ayzGJeIqECmVMULj33qRudD0C5KUfBAAw6pe_-Ha2ptP4S5UN4rKfwcSKe7AW7k5e1jdzAaNs0bugLdWEyJhgoTk1ctjfiCEVZmt6cjVdCjbWXP30bqpV5DYYUct0b6Ozk-uJDrl3mXhkvpt3RYF18bBqpaa4sWUm0OQMwCLG771PkUwpf5EQV3P4JXktEo66Zru74gyCTiGAXyvJUAW9ThhXgoW0s3xBP6HR4xMZo1JheRhr-SjKuA6_dZjYO0MQPnKA4UJtzNO9uNgZiriLKMTdf9FL4qxVewiSFxazT0fJC2717nDh6Ld8hXlsY1SjH__BM2ENrGewXJLzJpozNIEKTTqQQysd-S3iqiuAnzLqqx1SrPXG8pkBsQpg==

        SecurityHandlerImpl decryptor = new SecurityHandlerImpl(
                new JKSKeyHandler( "src/test/resources/testkeysV5/merchant.jks", "111111", "merchant", "ngcert"));

        decryptor.decrypt(ENCRYPTED);
    }
}
