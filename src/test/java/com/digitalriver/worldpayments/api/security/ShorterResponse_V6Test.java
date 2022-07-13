package com.digitalriver.worldpayments.api.security;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class ShorterResponse_V6Test {

	@Test
	public void ShorterResonseDecodedV6Test() {
		String DECODED = "ACA=PP_1657636376606;ACB=2;ACC=debit;";
		String ENCODED = "BgAAA-wE9qN3j5Uk2JmxpJAlFMT_oyheGbH-NBSW6ZPc8wUaKrMjUDXxKCTubYMtBtnCOnT59D2MoVWSnkXNOjYyWEC_ZzoJ8jER_geHZpLs3TtvPhJmDnWvgpUrQitiW6q2QFows_BkNP-lolyDhsH3vRBNBNZkZ9Ua930Pn8aO477xsVlboZD6QnXo57qa8VAdtr3dSzh_LolmJvyiw20AS6P48nWgSf97QAyJZUrdI7D8MVBYp_3rzvzbdkUJytbMHCMwHO9Jmei72MrcQNM97s-dlV1xzsxkmFnv58ALIVK3NJKVA-aSGa_oyUKWYon9bv8xaKSqEazWJ09ozB_SeqHYQUNBPVBQXzE2NTc2MzYzNzY2MDY7QUNCPTI7QUNDPWRlYml0Ow==";
		
		com.digitalriver.worldpayments.api.utils.ShortResponseUtil decryptor = new com.digitalriver.worldpayments.api.utils.ShortResponseUtil(
			new com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6("src/test/resources/new_merchant.jks", "merchant_ks",
						"1578446540", "drwp_cert"));
		String deco = decryptor.decodeWithBase64(ENCODED);
		assertThat(DECODED, equalTo(deco));
	}
}
