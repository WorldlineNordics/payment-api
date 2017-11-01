package com.digitalriver.worldpayments.api;

import org.junit.Assert;
import org.junit.Test;

public class PaymentPageHandlerTestRequiredFields extends PaymentPageHandlerTestBase {

    // check required
    @Test(expected = IllegalArgumentException.class)
    public void testNoMid() {
        PaymentPageRequest request = createValidRequest();
        request.setMid(null);
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString
                .startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoOrderid() {
        PaymentPageRequest request = createValidRequest();
        request.setOrderId(null);
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString
                .startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoCurrency() {
        PaymentPageRequest request = createValidRequest();
        request.setCurrency(null);
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString
                .startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoLang() {
        PaymentPageRequest request = createValidRequest();
        request.setConsumerLanguage(null);
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString
                .startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoCountry() {
        PaymentPageRequest request = createValidRequest();
        request.setConsumerCountry(null);
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString
                .startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoUrl() {
        PaymentPageRequest request = createValidRequest();
        request.setConsumerCountry(null);
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString
                .startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }
}
