package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.AbstractPaymentPageRequest;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PaymentHandlerTest extends PaymentHandlerTestBase {

	private <T> T getField(Object obj, String name, Class clazz) throws NoSuchFieldException, IllegalAccessException {
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		return (T) field.get(obj);
	}

	@Test
	public void testAutoCaptureTrue() throws NoSuchFieldException, IllegalAccessException {
		PaymentRequest request = buildRequest();
		String transactionType = getField(request, "transactionType", AbstractPaymentPageRequest.class);
		assertEquals(DEBIT, transactionType);
	}
	
	@Test
	public void testAutoCaptureFalse() throws NoSuchFieldException, IllegalAccessException {
		PaymentRequest request = buildRequestWhenAutoCaptureFalse();
		String transactionType = getField(request, "transactionType", AbstractPaymentPageRequest.class);
		assertEquals(AUTHORIZE, transactionType);
	}
	
	@Test
	public void testWhenNoTransactionChannel() throws NoSuchFieldException, IllegalAccessException {
		PaymentRequest request = buildRequestWhenNoTransactionChannel();
		paymentHandler.encryptRequest(request);
		String transactionChannel = getField(request, "transactionChannel", AbstractPaymentPageRequest.class);
		assertEquals(WEBONLINE, transactionChannel);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWhenTransactionChannelNull() {
		PaymentRequest request = buildRequestWhenTransactionChannelNull();
		paymentHandler.encryptRequest(request);
	}
	
	@Test
	public void testWhenDifferentTransactionChannel() throws NoSuchFieldException, IllegalAccessException {
		PaymentRequest request = buildRequest();
		paymentHandler.encryptRequest(request);
		String transactionChannel = getField(request, "transactionChannel", AbstractPaymentPageRequest.class);
		assertEquals(MAIL, transactionChannel);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWhenEmptyTransactionChannel() {
		PaymentRequest request = buildRequestWhenEmptyTransactionChannel();
		paymentHandler.encryptRequest(request);
	}
	
	@Test
	public void testHandlerUnpackResponse() {
		paymentHandler = new PaymentHandler(new JKSKeyHandlerV6("src/test/resources/new_merchant.jks", "merchant_ks", "1578446540", "drwp_cert"),
				"https://nowhere-noendpoint:12345678", "https://nowhere-noendpoint:12345678/paymentOptions");

		String responseString = "BhtQvMQAAAPsQgLCOHUvE9gK8Gm5kbDGHa1akPP1GKlPA405eVdeAqIgIkhLstItnHwfZesjQKkBhKNxiz0noO9O1WG6z1tNV99KksLOM6NMk_isMJxRl7ZVsP7PHJKeuoahuzWln41LvRVUcC0siE7gBHPB6RA1ZrVU3EKJSvAVxfW2TcdSiI-SZtbUtEhAp8nocUv9hdrbVo37oswcw6FpQQ4Mfbp6ngy9IFp0JmvktMHoVTTUqe-AIbTtf6q18rcgYPCjAo6MHqpDta1oh-kkW0mUPL0ZRSeeS2gr8lC4C1QosKQ2kXysPJK1cYwBCPSUu6fiyknVGh5_QFveMqPc3h6zbwGRxFCP6QY42FT9thi2jOZK0p8RWlwyhSLdyiD1krZxaxuxmx5tQq7N4YPlHBJaCrF7SmHrwdG4cISk4AtiKTv2H52eon_ia31RZ_qbhek5-DMbft7knPUc1CGrjdvfckHlfLa1V189SCYHbZJHvQoYhcUTdM5bYQDlfpxjLipKcKe3K6ySFAaQyvB4rR4fuKMz3110zhxXblRqbn5acYip5XmkzdRU7NQcN2Bzt_57ygC9LqqK4rc316TpJmea-HnrKLM0zA_lGhnb2R8r11k0ph-6Q6FR8cqKYb10otgiTmKrGSlrl30RpAjYF1FMPWTqlNw_-TbTulHul2Y3FMQuOSsuZquFK-Ay53R-wXzYp-FESnnJTZ1VMH6Vglu5S61BooYZiZdBWKOc9FmvX9BaHsD9xpJQ-k9SEjVksBO8mUXnKZ8bFDj7ewGYVmbkvK_S_WMjiOelgzFnkbvGTQyoDUkIkQ1e5LaEnKPi-Zp2k5V8o-bARKvt4ojrjOLoi82x69_uxYnzsGvfUFQf0MXpejhtSg-85gP-pK1aaZn0hLy7JpfYcpYyFxZUw-Y4Vy-0BUyltIzVI7pI81ygTENuxSsBPQYahg3qD8AoGKJFnN6bUZiQ4vpVhGDCgQHmjbiXufpEgDOtweFJDWWGkLT22WyhUVmaETnNYmzBhERHaFO4";
		PaymentResponse response = paymentHandler.unpackResponse(responseString);
		assertEquals(1578446540, response.getMid().longValue());
		assertEquals("Example_order_1625225639101", response.getOrderId());
		assertEquals("Visa", response.getPaymentMethodName());
		assertEquals(10258640519L, response.getTransaction().getTransactionId().longValue());
	}

	@Test
	public void testEndpoint() {
		String deviceEndpoint = "https://somewhere.com/path";
		PaymentHandler paymentHandler =
				new PaymentHandler(new JKSKeyHandlerV6("src/test/resources/merchant.jks",
						"merchant", "merchant",
						"drwp_cert"), deviceEndpoint);

		assertEquals(paymentHandler.deviceEndpoint, deviceEndpoint);

	}

	@Test
	public void testResponseJson() {
		String encryptedPayload = "abcx8:23lsdfdfdfASD%J";
		String deviceEndpoint = "https://abcd.b1234.com:1234";
		String responseJson = paymentHandler.getResponseJson(encryptedPayload, deviceEndpoint);

		JsonElement jelement = new JsonParser().parse(responseJson);
		JsonObject jobject = jelement.getAsJsonObject();

		assertEquals(encryptedPayload, jobject.get("encryptedPayload").getAsString());
		assertEquals(deviceEndpoint, jobject.get("deviceEndpoint").getAsString());
		assertEquals("A", jobject.get("version").getAsString());
	}
	
	@Test
	public void containsNoQuotes() {
		assertFalse(paymentHandler.hasQuotes("abcdef#%&¤"));
	}

	@Test
	public void containsQuotes() {
		assertTrue(paymentHandler.hasQuotes("abcde\"f#%&¤"));
	}

	@Test
	public void testCreateGetPaymentAPIRequest() {
		PaymentOptionsRequest req = new PaymentOptionsRequest("23456789", 123456789L, "pos123");
		String request = paymentHandler.createGetPaymentAPIRequest(req);
		assertTrue(request.contains("\"version\": \"A\""));
		assertTrue(request.contains("\"deviceEndpoint\": \"https://nowhere-noendpoint:12345678/paymentOptions\""));
	}
	
}
