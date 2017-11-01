package com.merchantcompany;

import com.digitalriver.worldpayments.api.AuthorizationType;
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
		request.setAmountDouble(123.0);
		request.setCurrency("SEK");
		request.setConsumerCountry("SE");
		request.setConsumerLanguage("sv");
		request.setReturnUrl("http://merchant.com?f=3&f=q");
		request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);
		return request;
	}

	public static void main(String[] args) {

		PaymentPageHandler paymentPageHandler = new PaymentPageHandler(
				PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL,
				new JKSKeyHandlerV6("src/test/resources/netgiro-2048.jks",
						"123456", "netgiro", "netgiro"));

		PaymentPageRequest request = buildRequest();

		String redirectUrl = paymentPageHandler.createRedirectUrl(request);

		System.out.println(redirectUrl);

		// sendRedirect(redirectUrl);
		// get back a response when consumer comes back

		String respString = "BgAAA-wAAAPqHZ-c1hJnrCiLG-JCvx7A-0lOGYWfYN6Uf8qH8Si15_22TvuQYX01VSCPsJNI5P_OkwtYv1xDhCmW8FPJora34L9bnL2UKSY9Zx8YpPjrCM3dTvM_ftoSDB_FXzlbkB6bwp_Vuc5U3L8FDbxAh6l3H1PZ3sjwe7Ew3Fx2qVv5DapTTuJ_RfyUCXi1w-ktNK0hIglACEg8H7zxMhqaLC15_O9_fYpqKBOgvvpTQyB3cBW2L7k-QhBF5SU1rKo_tAh56AtTMpJ1iojWHF4lCYykzR-MXzfThgZ0UxHheN9VYLL2r46XL7Zen3bv3t6_fQ4YFW37RR21THvhI0ho4JwKh0Q_pQ7qQkUjEGVR33MT88bIsH85-0iC_KrwVhFWbk-gYVPE6JnNy9FuSMkQ7Ax8MDBKkImJHqMgkJl-22UqihiYymYKl_90rLNjLlx7b0n2Mfa6yrJT4D8wRS5ZavrOISoupL9J5W9czs7g7oeLU7XoJEfz2efwTpamD90JxTokqmPr8vt-TWRNcZanKbAXQ-K7C7VZk5F28aGWBTeIEfTce7OTDusLpneRYOWQnOXAPMMkCbGGrID43tmBfIdhSmXCagqWU0ojSkdalRBZtvpE83Amjf_EvV6HE13KXykhZH5sH04w-a8bqfl8NLzpzgb-dCs8bFSFv8afKznobazDmEPjHygFqiW3fhdRqZdClO2CZtJedKajqudSoOyw9T_PVpju3l2R3cgPeIgUweh9i0BX4Q8iPqE6WDe8K2fibNxR18cgZ0yb5W4jofI_Mg919TMOfHdNonLT_lbS63cFjpaf9TUyt0ghkU16O3BhX7nN_tAzZbrD_sO-c9C3dcvMHBLmwII0OMgRTL9fvS4=";
		PaymentPageResponse response = paymentPageHandler
				.unpackResponse(respString);

		System.out.println("Response: mid=" + response.getMid() + ", orderid="
				+ response.getOrderId() + ", status="
				+ response.getStatus() );

	}
}
