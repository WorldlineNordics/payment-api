package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;
import org.junit.BeforeClass;

import java.math.BigDecimal;

public class PaymentHandlerTestBase {
	
	static PaymentHandler paymentHandler;
	public static final String AUTHORIZE = "AUTHORIZE";
	public static final String WEBONLINE = "Web Online";
	public static final String MAIL = "Mail";
	public static final String DEBIT = "DEBIT";
	
	@BeforeClass
	public static void setUp() {
		paymentHandler = new PaymentHandler(new JKSKeyHandlerV6("src/test/resources/merchant.jks", "merchant", "merchant", "drwp_cert"),
				"https://nowhere-noendpoint:12345678");
	}
	
	public PaymentRequest buildRequest() {
		PaymentRequest request = new PaymentRequestBuilder()
	    .setMid(1234567890L)
		.setPosId("ABC")
		.setTransactionChannel("Mail")
		.setOrderId("orderid")
		.setAmount(new BigDecimal(100.00))
		.setCurrency("SEK")
		.setConsumerCountry("SE")
		.setConsumerLanguage("sv")
		.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION)
		.setAutoCapture(true)
		.createPaymentRequest();
		return request;
		
	}
	
	public PaymentRequest buildRequestWhenAutoCaptureFalse() {
		PaymentRequest request = new PaymentRequestBuilder()
		.setMid(1234567890L)
		.setPosId("ABC")
		.setTransactionChannel("Web Online")
		.setOrderId("orderid")
		.setAmount(new BigDecimal(100.00))
		.setCurrency("SEK")
		.setConsumerCountry("SE")
		.setConsumerLanguage("sv")
		.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION)
		.setAutoCapture(false)
		.createPaymentRequest();
		return request;
	}
	
	public PaymentRequest buildRequestWhenNoTransactionChannel() {
		PaymentRequest request = new PaymentRequestBuilder()
		.setMid(1234567890L)
		.setPosId("ABC")
		.setOrderId("orderid")
		.setAmount(new BigDecimal(100.00))
		.setCurrency("SEK")
		.setConsumerCountry("SE")
		.setConsumerLanguage("sv")
		.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION)
		.setAutoCapture(false)
		.createPaymentRequest();
		return request;
	}
	
	public PaymentRequest buildRequestWhenTransactionChannelNull() {
		PaymentRequest request = new PaymentRequestBuilder()
		.setMid(1234567890L)
		.setPosId("ABC")
		.setOrderId("orderid")
		.setTransactionChannel(null)
		.setAmount(new BigDecimal(100.00))
		.setCurrency("SEK")
		.setConsumerCountry("SE")
		.setConsumerLanguage("sv")
		.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION)
		.setAutoCapture(false)
		.createPaymentRequest();
		return request;
	}
	
	public PaymentRequest buildRequestWhenEmptyTransactionChannel() {
		PaymentRequest request = new PaymentRequestBuilder()
		.setMid(1234567890L)
		.setPosId("ABC")
		.setOrderId("orderid")
		.setTransactionChannel(new String(""))
		.setAmount(new BigDecimal(100.00))
		.setCurrency("SEK")
		.setConsumerCountry("SE")
		.setConsumerLanguage("sv")
		.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION)
		.setAutoCapture(false)
		.createPaymentRequest();
		return request;
	}
}
