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
	
	/*@Test
	public void testHandlerUnpackResponse() {

		String responseString = "BgAAA-sAAAPqLmG9awk-ZuE00dxJc0fjlsCQsSTSSfpRdCtrGIMXnhIBHGm-gnhdFG0vKX_t1CttI1Ss2ySGScKgDJPYhxO6wcjL22u932xZAdN72fo5vFq-3pdm5SSehDCLv0IWj5qfwBzPCB2QCE_kW-N-HbrRpjYN6c3h-Kv_h0kVRESPQd7pSaL86XRfl02f-peCThc4KpHMPRBUCVVjPQQfOJx6YqrNqmBjcTorrCzNlDXRruGIdlgrPxyKr2TmyhKs-v-05G_4B4ZEdkSrmun1ulDoVFYlzcTuXGr4o-mwHG8ZrLpLWYL47ODUTxgyOghJe6lw7IsB4fI9kU2ka7-unAj5CmIoI5lW7TjNNqkU_jUIDkq40WxuMHJujWuyO1qIng5jvKLIcB8B7kTFMpDmjbwpp0371Qfz6zly8OzTZprKhydoVZu9myeqaOBgb9agvhTfRdzo0LHrsL0hKJcSIRD4y_xFhWqY4KNMJ_wHi4huwr1YvNqg2eNBeu1sb8g4ZsZ8Td0mvPEeSaLkSaQzZoviQP9knV9vVeWEqrojHpzN9vJ1ckyFdhHcs9iaodxDHfbeRdusmoCQ2XtiRnmXxW-TqN1-97vundXHEPiEUwMI8eU06bluOb-MGpuE2hTQhJtLMoWqWfS_GSK6sQb6kT_bUf7J9vHzzo1aXS4HNPDVkb_7WjYBrqLk6tvWdYFnp9C4DSZ42XU_HmlsAjpf_1lyDplxh9uIXFAl2PtSxWvLIrMSz6cSSDOKld2mbwgiXhT8exbklticT2OiFo_U5bF6-2XqYp0bvpVFh_cms-0D76_9X5hovg0vO1T0H1WKqfrQSprSWZ76S6CS1y0E2E9O815GC7SPAFD0SIRt2kmV0xIh254S7uX9oSrzGvwOwFjOtx8LU3zjpL6g1mLYUGxWiwPhtPl2_SNGBCcK0MhVh8Gl4zs5Ijj7HM7jbyjdxwxr-PkJdqfj14skZdXbBZ8H0uH07GycueXDngbjDyCI6XWigsIV_-n52kO1r8CDYi2wU2z2JPL2fqsPeBvoZQzAaQ==";
		PaymentResponse response = paymentHandler.unpackResponse(responseString);
		assertEquals(1950473647L, response.getMid().longValue());
		assertEquals("PP_1501256594620", response.getOrderId());
		assertEquals("Visa", response.getPaymentMethodName());
		assertEquals(4323160870L, response.getTransaction().getTransactionId().longValue());
	}*/

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
