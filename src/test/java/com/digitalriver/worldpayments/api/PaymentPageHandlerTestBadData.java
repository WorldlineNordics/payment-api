package com.digitalriver.worldpayments.api;

import org.junit.Assert;
import org.junit.Test;

public class PaymentPageHandlerTestBadData extends PaymentPageHandlerTestBase {

    @Test(expected = IllegalArgumentException.class)
    public void testBadMid() {
        PaymentPageRequest request = createValidRequest();
        request.setMid(12345L);
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadByerType() {
        PaymentPageRequest request = createValidRequest();
        request.setBillingBuyerType("kalle");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrderidTooLong() {
        PaymentPageRequest request = createValidRequest();
        request.setOrderId("12345678901234567890123456789012345678901234567890123");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPosTooLong() {
        PaymentPageRequest request = createValidRequest();
        request.setPosId("12345678901234567890123456789012345678901234567890123");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadCurrenyNumeric() {
        PaymentPageRequest request = createValidRequest();
        request.setCurrency("123");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadCurrenyTooShort() {
        PaymentPageRequest request = createValidRequest();
        request.setCurrency("AA");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadLangUpperCase() {
        PaymentPageRequest request = createValidRequest();
        request.setConsumerLanguage("SV");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadLangTooShort() {
        PaymentPageRequest request = createValidRequest();
        request.setConsumerLanguage("A");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadCountryLowCase() {
        PaymentPageRequest request = createValidRequest();
        request.setConsumerCountry("se");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadCountryTooLong() {
        PaymentPageRequest request = createValidRequest();
        request.setConsumerCountry("SWE");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadTxType() {
        PaymentPageRequest request = createValidRequest();
        request.setTransactionType("debbit");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRequestValue() {
        PaymentPageRequest request = new PaymentPageRequest();
        paymentPageHandler.createRedirectUrl(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadRecurringType() {
        PaymentPageRequest request = createValidRequest();
        request.setRecurringType("TEST");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadPosDescription_BadLength() {
        PaymentPageRequest request = createValidRequest();
        request.setPosDesc("This is very long point of sale description. Allowed lenght is 50.");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    // Expected format is yyyymmdd
    @Test(expected = IllegalArgumentException.class)
    public void testBirthDate_ddyyyyMM() {
        PaymentPageRequest request = createValidRequest();
        request.setBirthDate("15198304");
        request.setCompanyResponsibleBirthDate("21198310");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    // Expected format is yyyymmdd
    @Test(expected = IllegalArgumentException.class)
    public void testBirthDate_MMyyyydd() {
        PaymentPageRequest request = createValidRequest();
        request.setBirthDate("04198315");
        request.setCompanyResponsibleBirthDate("10198301");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    // Expected format is yyyymmdd
    @Test(expected = IllegalArgumentException.class)
    public void testBirthDate_ddMMyyyy() {
        PaymentPageRequest request = createValidRequest();
        request.setBirthDate("15041983");
        request.setCompanyResponsibleBirthDate("05101983");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    // Expected format is yyyymmdd
    @Test(expected = IllegalArgumentException.class)
    public void testBirthDate_MMddyyyy() {
        PaymentPageRequest request = createValidRequest();
        request.setBirthDate("04151983");
        request.setCompanyResponsibleBirthDate("10051983");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    // Expected format is yyyymmdd
    @Test(expected = IllegalArgumentException.class)
    public void testBirthDate_yyyyddMM() {
        PaymentPageRequest request = createValidRequest();
        request.setBirthDate("19831504");
        request.setCompanyResponsibleBirthDate("19831510");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }

    // Expected format is yyyymmdd
    @Test
    public void testBirthDate_yyyyMMdd_OK_Date() {
        PaymentPageRequest request = createValidRequest();
        request.setBirthDate("19830415");
        request.setCompanyResponsibleBirthDate("20001005");
        String requestString = paymentPageHandler.createRedirectUrl(request);
        Assert.assertTrue(requestString.startsWith(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL));
    }
}
