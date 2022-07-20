package com.digitalriver.worldpayments.api.security;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class ShorterResponse_V6Test {

	@Test
	public void ShorterResonseDecodedV6Test() {
		String DECODED = "ACA=PP_1658295222169;ACB=OK;ACC=Debit;";
		String ENCODED = "BgAAA-wXf10G91CizAKsGJH5P48MjKnvTH0kM7VuQPabSbhrZPyosvCo5t6CJza3GebzYpoLJH3QkdQ25VnxbfQxQcDyV3gT-0uc59ykhS5FKy26Azb0tJ3DVrUh38BfCcoWWvKgxjHIkV5xXhVzT1XDcabbcUw6dI204M62w5Clw4lOE_n0bO1j_4KfLhG0DMz9VbEZNdLOTvR4eAq3zOQIVTJYAbdUtQC7W7Rts2-ck39b2T00LCoEcR5i0nv1mFb6lY6PeK_D2evcMXJOzhzY8GBXcRNiV7t8ifPPlaw8_Kp93EHJoxlOPFCAmcpDicJcor7ZfJTu9gS0jf1rJiEB2BOBH4sIAAAAAAAAAHN0drQNCIg3NDO1MLI0NTIyMjSztHZ0drL19wZSzrYuqUmZJdYAQaGe8iYAAAA=";
		
		com.digitalriver.worldpayments.api.utils.ShorterResponseUtil decryptor = new com.digitalriver.worldpayments.api.utils.ShorterResponseUtil(
			new com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6("src/test/resources/new_merchant.jks", "merchant_ks",
						"1578446540", "drwp_cert"));
		String decoded = decryptor.decodeWithBase64(ENCODED);
		assertThat(DECODED, equalTo(decoded));
	}
}
