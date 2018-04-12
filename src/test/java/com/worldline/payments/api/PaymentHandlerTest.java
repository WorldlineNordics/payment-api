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

		String responseString = "BgAAA-oAAAPsp8gylxPew0H0Vr7XLucnN3fsocwCFBN1rUW_9TROMrnXQWZe8Book1K7bdjbC3FfL1SeQicVieKVCh5J-OhrZgr3l-LWnTK8DzeGQmLYxDUKkRcsgrf3Z0_rE-NiVm43qOX7L59VjqVfr05OzHbNflECi6blnLNDgRzzav0TzB03kVO-kW0jyZdXGwgoXtMPFvZNse99Vjn_yaW98dLCg-lvYbm5ff4W3NS6fW11Dy3LeFvdsZ0_cw0zd8t6lynP4sRl4KTSlmwI68DLm9PjfO0wbrbgDhHx49TDlFyqk5F8kS6lk_if9-HTQd4S2LIQdJaazBr-RXAnzHTR7LdVyLD2oxbjfwiRUcMNZm7HyANiBpruMDcIjTIHA665diNAd9Y4Z3Lx--FRPKS_fmmBgg8UcraNlGBln_IztnBhdErz1PTl_qkw4F6jG3fQrOZWIismg58NnaxJmue6LRUFOIU_2KZ4k3ub1uV_0jyCV4pSD-S8fPidNB91q7UB2UOHoNAhWNwLS8PJ-nCWpAQtImtGWxl9mJUxWGLq6cOTHbYf9XRLmkmewvI50nqdswC0wnLfe9a71MkvsLecIPxRqsI4MVDH_bQx3_j0Q_-0Ub4A6bfAQzgIDX41koWbA9XR6p4CNYHZhHpqIC5lAb6WT7w4mfj4OGJoP5pRAonpb8A1iVdn4O1SwTfJ18aHisDyZeuc_WC1YEDtJEUPWPRgWxdC8NLoamqNWq446KBxJaV6hQyjWOu922j4hbMtGBHjduynrRtr2NR5QVMdbuYzFCQfiOBcIu3j70-UpVq62cUCttRzt_RjAYnfBmb7DTq1Sjkw74W94Y9VoiCfa0Ibei865wwULQn0qxh7iNsskr1epUI7pMizNBfB6fjWLm3Ny47D5ZR7ite1YXPgtijH3LaJZ76R47Eud82LIzXVOCykKRZTbhurtMS8crJR9pOoTmk826W44pJFrUrRRLClsKe6Agf2ZHdMzUCMmnIFvQY\u003d";
		PaymentResponse response = paymentHandler.unpackResponse(responseString);
		assertEquals(1640156216, response.getMid().longValue());
		assertEquals("Example_order_1516617784095", response.getOrderId());
		assertEquals("Visa", response.getPaymentMethodName());
		assertEquals(5022637728L, response.getTransaction().getTransactionId().longValue());
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
		PaymentOptionsRequest req = new PaymentOptionsRequest("23456789", 123456789L);
		String request = paymentHandler.createGetPaymentAPIRequest(req);
		assertTrue(request.contains("\"version\": \"A\""));
		assertTrue(request.contains("\"deviceEndpoint\": \"https://nowhere-noendpoint:12345678/paymentOptions\""));
	}
	
}
