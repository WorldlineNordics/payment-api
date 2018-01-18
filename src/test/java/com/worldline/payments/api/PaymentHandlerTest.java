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

		String responseString = "BgAAA-oAAAPssk8CrFbYZL-AuKRSl-ey326mCaxCe5uOxdXC6pxM1-hiUJ4l1p9GSRdKHzk_op8AVX9JUqpm7onOczQsHhKUlZmk5xF06pFpB_NKn26pp37VAHZ4jUzk8jCv4RjxNLwQsQTGFrAl8-1s2hmVEEDhHKWrqewz_Nn0Mz-6kxItu3h4mPcBML_a5PgBTGZWFskAVuWAvNlaP-HRjdx11sqbPiey_z29uaYe9AbMQGSytrGRnoppGpPlsbtFweSHjuJe84v0gipoSlNwtlXUs7y2Mh2_raOyx-GtWytyNW4zg-PE0RNBuCilAfx8utJTxsDoYNvmWZq338WbiH20ej0Ak07KNGKeHNtcVbLG3KTE4PlHy1MwnXTrw2zLwOVpWZPwUhQNPVl8uAKarvwaWf0toVj0Stuou8qS5zilYYJRsF-kAidnzTL6ysYTfJ-XH-BxL12Y-yCRSzefXzkymXtfksOFuBf2JpkcGVX1bqLLuiKIrn5KEdKrEeAdZs70iOq2ViN_ZQ75Vd6YD_WWUn-xrii-FVX0Ds6Km0PtDH8YqEotyxoq9n5NaiAZsEMlxdN8MEzWG2yNsAXqXgr9jRA1VxeDH9N4kXv_cOlxplysZyIfuW5G1PfO_8vN6I3ec12x3_9UqxG-N9R6QiAqzTqFfVkeVKe2drMeWzaxyGeEa357VLMyTl9i0kD-ctp73PWdXpdWhxhEVMJJGN_KroJDxkWWnSL2o0_tVgN1GLTG2G_4xi3zCsP_ehHhbhhKrI2Fag3xwS5dtTpZ9-45YAKbPJNoNz7pDv-uvU71JlcKJCl1mk7mRR2BUCztFoEDoc98wedy57VCTDefoiL_uuPTVkQlT9J6gjN-biVaVzmX9o41pZ51L_9IWrRBJNkKy4INm7fb9x8GPuxyRrBkXesWNqU8sU6vAQjPaCmKGxoAdNO1FEzYEqD4uE9OlfVX1dv31sSkDoICglz7_0E0V9zdlmmnmTdP1-jTO1u15YKmtXI=";
		PaymentResponse response = paymentHandler.unpackResponse(responseString);
		assertEquals(1640156216, response.getMid().longValue());
		assertEquals("Example_order_1516278991954", response.getOrderId());
		assertEquals("Visa", response.getPaymentMethodName());
		assertEquals(6009158407L, response.getTransaction().getTransactionId().longValue());
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

}
