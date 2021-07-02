package com.digitalriver.worldpayments.api.security;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SecurityHandler_V6Test {
	
	@Test
	public void testEncryptV6(){
        com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl encryptor = new com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl(
                new com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6(
                        "src/test/resources/merchant2048.jks", "x923##.Lkk",
                        "merchant", "ngcert"));
        String DECRYPTED = "A=123456789;C=123;E=OrderId12345;F=2011-03-31 14:04:15 CEST;T=true";
        // No exceptions expected
        encryptor.encrypt(DECRYPTED);
	}
    
	@Test
	public void testDecryptV6() {
		String DECRYPTED = "A=1578446540;B=OK;C=10258640519;D=CARD;E=Example_order_1625225639101;J=0;AAG=Debit;L=10258640519;M=UNKNOWN;N=44026317;P=01-2023;Q=UNKNOWN;U=444433XXXXXX1111;V=444433XXXXXX1111;W=01-2023;AC=0;AD=;AE=00;AF=0;AG=0;AH=;AI=Visa;AJ=10258640519;AU=;AAE=Transaction successfully processed;N=44026317;AAF=Processed;AAH=100.00;AAI=100.00;AAJ=100.00;AAK=0.00;AAL=EUR;ABB=;";
		// This string has been encrypted in the PPS with the corresponding
		// private key there and is known to represent the content in DECRYPTED.
		String ENCRYPTED = "BhtQvMQAAAPsQgLCOHUvE9gK8Gm5kbDGHa1akPP1GKlPA405eVdeAqIgIkhLstItnHwfZesjQKkBhKNxiz0noO9O1WG6z1tNV99KksLOM6NMk_isMJxRl7ZVsP7PHJKeuoahuzWln41LvRVUcC0siE7gBHPB6RA1ZrVU3EKJSvAVxfW2TcdSiI-SZtbUtEhAp8nocUv9hdrbVo37oswcw6FpQQ4Mfbp6ngy9IFp0JmvktMHoVTTUqe-AIbTtf6q18rcgYPCjAo6MHqpDta1oh-kkW0mUPL0ZRSeeS2gr8lC4C1QosKQ2kXysPJK1cYwBCPSUu6fiyknVGh5_QFveMqPc3h6zbwGRxFCP6QY42FT9thi2jOZK0p8RWlwyhSLdyiD1krZxaxuxmx5tQq7N4YPlHBJaCrF7SmHrwdG4cISk4AtiKTv2H52eon_ia31RZ_qbhek5-DMbft7knPUc1CGrjdvfckHlfLa1V189SCYHbZJHvQoYhcUTdM5bYQDlfpxjLipKcKe3K6ySFAaQyvB4rR4fuKMz3110zhxXblRqbn5acYip5XmkzdRU7NQcN2Bzt_57ygC9LqqK4rc316TpJmea-HnrKLM0zA_lGhnb2R8r11k0ph-6Q6FR8cqKYb10otgiTmKrGSlrl30RpAjYF1FMPWTqlNw_-TbTulHul2Y3FMQuOSsuZquFK-Ay53R-wXzYp-FESnnJTZ1VMH6Vglu5S61BooYZiZdBWKOc9FmvX9BaHsD9xpJQ-k9SEjVksBO8mUXnKZ8bFDj7ewGYVmbkvK_S_WMjiOelgzFnkbvGTQyoDUkIkQ1e5LaEnKPi-Zp2k5V8o-bARKvt4ojrjOLoi82x69_uxYnzsGvfUFQf0MXpejhtSg-85gP-pK1aaZn0hLy7JpfYcpYyFxZUw-Y4Vy-0BUyltIzVI7pI81ygTENuxSsBPQYahg3qD8AoGKJFnN6bUZiQ4vpVhGDCgQHmjbiXufpEgDOtweFJDWWGkLT22WyhUVmaETnNYmzBhERHaFO4";

		com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl decryptor = new com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl(
				new com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6(
						"src/test/resources/new_merchant.jks", "merchant_ks",
						"1578446540", "drwp_cert"));

		String decr = decryptor.decrypt(ENCRYPTED);
		assertThat(DECRYPTED, equalTo(decr));
	}
}
