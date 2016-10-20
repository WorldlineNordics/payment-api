package com.merchantcompany;

import com.digitalriver.worldpayments.api.PaymentPageHandler;
import com.digitalriver.worldpayments.api.PaymentPageRequest;
import com.digitalriver.worldpayments.api.PaymentPageResponse;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;

public class Example {

	private static PaymentPageRequest buildRequest() {
		PaymentPageRequest request = new PaymentPageRequest();
		request.setMid(1234567890L);
		request.setSubMerchantId("12");
		request.setPosId("ABC");
		request.setTransactionChannel("Web Online");
		request.setOrderId("orderid");
		request.setAmount(123.0);
		request.setCurrency("SEK");
		request.setConsumerCountry("SE");
		request.setConsumerLanguage("sv");
		request.setReturnUrl("http://merchant.com?f=3&f=q");
		return request;
	}

	public static void main(String[] args) {

		PaymentPageHandler paymentPageHandler = new PaymentPageHandler(
				PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL,
				new JKSKeyHandlerV6("src/resources_test/testkeys/java/merchant.jks",
						"merchant", "merchant", "drwp_cert"));

		PaymentPageRequest request = buildRequest();

		String redirectUrl = paymentPageHandler.createRedirectUrl(request);

		System.out.println(redirectUrl);

		// sendRedirect(redirectUrl);
		// get back a response when consumer comes back

		String respString = "BEii9iVIovcIXe2rbjt31F7LixHliaguahcPN-YJF9HFzln_NDbJgRBp01pLjIy3su5FjzW-rPXO0hpAwhL6-fI2PjTxQ0posvZqX3PVQiAwWvWgGTFTMnbItbo_V6fQBJrZDRF_TdyH92KMP4RHj8yACK8F_Zm_r43-stm1w6K6dNBx4JXCG0BWlSY9ToFSU6gngfEdiYa__d6Yid9w2YwgSDWUN-3qRy83uJ3LhGhF5dETLuRa0iW9_gveowjtq8lvksn1LwF2PSHFA5p94XPGaepjbZMly0BB8EKU0MeLHdtBIhiK4kJseeyPEFif34zXOyFdqmci-Yh3LsGMwHJKVe5aQWDAua81YZBpXMA4PlYa_aFvUp3UC3oA9RI8ZGQNkqTiCbJJce_iSg1by9ZX6AtEuOmDk8WkRd4EUeZuEXBh3sYB6p3CyE_ESOCvXAseu8GsksTZAhIToRUeJ3SCkNy3BO9f9pTpfFieBl9JTGFjjlmt1DoaTO9JZ4B7wvmNfY7Z9CNVQmHyyl7zGtz9G1ldlEt9d80LxwreGLf-EMrevfpUQb6jmEH1QqsUp2ExNiu4qQJYobs8tMw-OJkWnjtcSLL8-g==";
		PaymentPageResponse response = paymentPageHandler
				.unpackResponse(respString);

		System.out.println("Response: mid=" + response.getMid() + ", orderid="
				+ response.getOrderId() + ", status="
				+ response.getStatus() );

	}
}
