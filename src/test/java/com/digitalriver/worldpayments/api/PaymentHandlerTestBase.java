package com.digitalriver.worldpayments.api;

import java.math.BigDecimal;

import org.junit.BeforeClass;

import com.digitalriver.worldpayments.api.AuthorizationType;
import com.digitalriver.worldpayments.api.PaymentHandler;
import com.digitalriver.worldpayments.api.PaymentRequest;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;

public class PaymentHandlerTestBase {
	
	static PaymentHandler paymentHandler;
	public static final String AUTHORIZE = "AUTHORIZE";
	public static final String WEBONLINE = "Web Online";
	public static final String MAIL = "Mail";
	public static final String DEBIT = "DEBIT";
	
	@BeforeClass
	public static void setUp() {
		paymentHandler = new PaymentHandler(new JKSKeyHandlerV6("src/test/resources/merchant.jks", "merchant", "merchant", "drwp_cert"));
	}
	
	public PaymentRequest buildRequest() {
		PaymentRequest request = new PaymentRequest();
		request.setMid(1234567890L);
		request.setPosId("ABC");
		request.setTransactionChannel("Mail");
		request.setOrderId("orderid");
		request.setAmount(new BigDecimal(100.00));
		request.setCurrency("SEK");
		request.setConsumerCountry("SE");
		request.setConsumerLanguage("sv");
		request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);
		request.setAutoCapture(true);
		return request;
	}
	
	public PaymentRequest buildRequestWhenAutoCaptureFalse() {
		PaymentRequest request = new PaymentRequest();
		request.setMid(1234567890L);
		request.setPosId("ABC");
		request.setTransactionChannel("Web Online");
		request.setOrderId("orderid");
		request.setAmount(new BigDecimal(100.00));
		request.setCurrency("SEK");
		request.setConsumerCountry("SE");
		request.setConsumerLanguage("sv");
		request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);
		request.setAutoCapture(false);
		return request;
	}
	
	public PaymentRequest buildRequestWhenNoTransactionChannel() {
		PaymentRequest request = new PaymentRequest();
		request.setMid(1234567890L);
		request.setPosId("ABC");
		request.setOrderId("orderid");
		request.setAmount(new BigDecimal(100.00));
		request.setCurrency("SEK");
		request.setConsumerCountry("SE");
		request.setConsumerLanguage("sv");
		request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);
		request.setAutoCapture(false);
		return request;
	}
	
	public PaymentRequest buildRequestWhenTransactionChannelNull() {
		PaymentRequest request = new PaymentRequest();
		request.setMid(1234567890L);
		request.setPosId("ABC");
		request.setOrderId("orderid");
		request.setTransactionChannel(null);
		request.setAmount(new BigDecimal(100.00));
		request.setCurrency("SEK");
		request.setConsumerCountry("SE");
		request.setConsumerLanguage("sv");
		request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);
		request.setAutoCapture(false);
		return request;
	}
	
	public PaymentRequest buildRequestWhenEmptyTransactionChannel() {
		PaymentRequest request = new PaymentRequest();
		request.setMid(1234567890L);
		request.setPosId("ABC");
		request.setOrderId("orderid");
		request.setTransactionChannel(new String(""));
		request.setAmount(new BigDecimal(100.00));
		request.setCurrency("SEK");
		request.setConsumerCountry("SE");
		request.setConsumerLanguage("sv");
		request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);
		request.setAutoCapture(false);
		return request;
	}
}
