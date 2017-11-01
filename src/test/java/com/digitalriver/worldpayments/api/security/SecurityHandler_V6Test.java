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
		String DECRYPTED = "A=123456789;C=123;E=OrderId12345;F=2011-03-31 14:04:15 CEST;T=true";
		// This string has been encrypted in the PPS with the corresponding
		// private key there and is known to represent the content in DECRYPTED.
		String ENCRYPTED = "BgAAA-oAAAPsFSPVHwi1VtrErYLC4q4K4AqbKRHiaUh4pNk9ET64oj21Zs0DAsuyQ86J0kB3magwoTjh0Pn0yHOEQuSQBePaaBaWs3FaiQcugB1byED-vxq5SZPWjPCMakxMb3CtGDyiOMtkNvcPhNx9d1J1XEDpcbAlxlIw5ypqi3UrWxZsr-Cv4zOrJ8qurdh4fEeK_L5XS4dkYLi51WlVSPFn77xCFlHrD1dXeVvraYOpr89t_B51oSte4q96_uF8jWYcEjYnhZMPinZD-Zs4eSQ6z7WUG8TwxWLVj9dPp9AKdUR7UUEoboiNkFwQX9h0Ij0cQ5YfmURlXlHdOGFikQhMrPYplryzVKu5fol3hbqjQHShnLatFw1pGWvd93HCg8Zi64WliPk67iaUEN8jVyCsVsXyFfbqMPSOtdeS8nwvkyyU6QMsRljgUjctvdDK0BZ8y0ZL8x0-NB-6V9HH8M45qpI-CfFF41vf-iTOoeQSDgBNbstlhUjkmED2KPbPyp4Akjl7b-VGjARdqj-5ufqkA97Ff58ns4XHTws498v1spd1cjeqof5iFLgOBB4endAEkPmafhCIMatNaabJD0oUWwBnfzdTUSbrycKjGAZyKH4zNLduzvuO1Rf8eHXaSQIaazBuQ0MG9NnpMtKYBg9vELGMTYzYP7w2NnMhktt6BWzotNEv0uJsAG4F66RDtFbosYwttjI7klB7gwYi0C3m4vUq6vBlZPfPyzMKRWyBkwv0H7pJcx0JdF5dDzwDIvj9TDK4ThbfCBWFI7eEHCcG2uAbjVSjW_s1qy9WeN-ejDwBUVg=";

		com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl decryptor = new com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl(
				new com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6(
						"src/test/resources/merchant2048.jks", "x923##.Lkk",
						"merchant", "ngcert"));

		String decr = decryptor.decrypt(ENCRYPTED);
		assertThat(DECRYPTED, equalTo(decr));
	}
}
