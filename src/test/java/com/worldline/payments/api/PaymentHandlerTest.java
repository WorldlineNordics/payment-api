package com.worldline.payments.api;

import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Test;
import com.digitalriver.worldpayments.api.AbstractPaymentPageRequest;

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
		Assert.assertEquals(DEBIT, transactionType);
	}
	
	@Test
	public void testAutoCaptureFalse() throws NoSuchFieldException, IllegalAccessException {
		PaymentRequest request = buildRequestWhenAutoCaptureFalse();
		String transactionType = getField(request, "transactionType", AbstractPaymentPageRequest.class);
		Assert.assertEquals(AUTHORIZE, transactionType);
	}
	
	@Test
	public void testWhenNoTransactionChannel() throws NoSuchFieldException, IllegalAccessException {
		PaymentRequest request = buildRequestWhenNoTransactionChannel();
		paymentHandler.encryptRequest(request);
		String transactionChannel = getField(request, "transactionChannel", AbstractPaymentPageRequest.class);
		Assert.assertEquals(WEBONLINE, transactionChannel);
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
		Assert.assertEquals(MAIL, transactionChannel);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWhenEmptyTransactionChannel() {
		PaymentRequest request = buildRequestWhenEmptyTransactionChannel();
		paymentHandler.encryptRequest(request);
	}
	
	@Test
	public void testHandlerUnpackResponse() {

		String responseString = "BgAAA-sAAAPqLmG9awk-ZuE00dxJc0fjlsCQsSTSSfpRdCtrGIMXnhIBHGm-gnhdFG0vKX_t1CttI1Ss2ySGScKgDJPYhxO6wcjL22u932xZAdN72fo5vFq-3pdm5SSehDCLv0IWj5qfwBzPCB2QCE_kW-N-HbrRpjYN6c3h-Kv_h0kVRESPQd7pSaL86XRfl02f-peCThc4KpHMPRBUCVVjPQQfOJx6YqrNqmBjcTorrCzNlDXRruGIdlgrPxyKr2TmyhKs-v-05G_4B4ZEdkSrmun1ulDoVFYlzcTuXGr4o-mwHG8ZrLpLWYL47ODUTxgyOghJe6lw7IsB4fI9kU2ka7-unAj5CmIoI5lW7TjNNqkU_jUIDkq40WxuMHJujWuyO1qIng5jvKLIcB8B7kTFMpDmjbwpp0371Qfz6zly8OzTZprKhydoVZu9myeqaOBgb9agvhTfRdzo0LHrsL0hKJcSIRD4y_xFhWqY4KNMJ_wHi4huwr1YvNqg2eNBeu1sb8g4ZsZ8Td0mvPEeSaLkSaQzZoviQP9knV9vVeWEqrojHpzN9vJ1ckyFdhHcs9iaodxDHfbeRdusmoCQ2XtiRnmXxW-TqN1-97vundXHEPiEUwMI8eU06bluOb-MGpuE2hTQhJtLMoWqWfS_GSK6sQb6kT_bUf7J9vHzzo1aXS4HNPDVkb_7WjYBrqLk6tvWdYFnp9C4DSZ42XU_HmlsAjpf_1lyDplxh9uIXFAl2PtSxWvLIrMSz6cSSDOKld2mbwgiXhT8exbklticT2OiFo_U5bF6-2XqYp0bvpVFh_cms-0D76_9X5hovg0vO1T0H1WKqfrQSprSWZ76S6CS1y0E2E9O815GC7SPAFD0SIRt2kmV0xIh254S7uX9oSrzGvwOwFjOtx8LU3zjpL6g1mLYUGxWiwPhtPl2_SNGBCcK0MhVh8Gl4zs5Ijj7HM7jbyjdxwxr-PkJdqfj14skZdXbBZ8H0uH07GycueXDngbjDyCI6XWigsIV_-n52kO1r8CDYi2wU2z2JPL2fqsPeBvoZQzAaQ==";
		PaymentResponse response = paymentHandler.unpackResponse(responseString);
		Assert.assertEquals(1950473647L, response.getMid().longValue());
		Assert.assertEquals("PP_1501256594620", response.getOrderId());
		Assert.assertEquals("Visa", response.getPaymentMethodName());
		Assert.assertEquals(4323160870L, response.getTransaction().getTransactionId().longValue());
	}

}
