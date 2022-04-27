package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.AbstractPaymentPageRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PaymentRequestBuilderTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    private <T> T getField(Object obj, String name, Class clazz) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return (T) field.get(obj);
    }


    @Test
    public void setMid() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(2234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")
                .createPaymentRequest();

        Long myField = getField(pr, "mid", AbstractPaymentPageRequest.class);
        assertTrue(myField.equals(2234567890L));
    }

    @Test
    public void setWrongMid() {
        try {
            new PaymentRequestBuilder()
                    .setMid(12345L)
                    .setOrderId("general-test")
                    .setConsumerCountry("UK")
                    .setConsumerLanguage("en")
                    .setCurrency("GBP")
                    .createPaymentRequest();
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Did not render exception.");
    }

    @Test
    public void setSubMerchantId() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("SEK")

                .setSubMerchantId("Test")
                .createPaymentRequest();

        String subMerchantId = getField(pr, "subMerchantId", AbstractPaymentPageRequest.class);
        assertEquals("Test", subMerchantId);
    }

    @Test
    public void setPosId() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("SEK")

                .setPosId("Test")
                .createPaymentRequest();

        String value = getField(pr, "posId", AbstractPaymentPageRequest.class);
        assertEquals("Test", value);
    }

    @Test
    public void setPosDefaultId() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("SEK")
                .createPaymentRequest();

        String value = getField(pr, "posId", AbstractPaymentPageRequest.class);
        assertEquals("0", value);

    }

    @Test
    public void setTransactionChannel() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setTransactionChannel("Fax")
                .createPaymentRequest();

        String value = getField(pr, "transactionChannel", AbstractPaymentPageRequest.class);
        assertEquals("Fax", value);

    }

    @Test
    public void setTransactionChannelCashRegister() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setTransactionChannel("Cash Register")
                .createPaymentRequest();

        String value = getField(pr, "transactionChannel", AbstractPaymentPageRequest.class);
        assertEquals("Cash Register", value);

    }

    @Test
    public void setTransactionChannelDefault() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")
                .createPaymentRequest();

        String value = getField(pr, "transactionChannel", AbstractPaymentPageRequest.class);
        assertEquals("Web Online", value);

    }

    @Test
    public void setToken() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setToken("tokenid-1234567890123456")
                .createPaymentRequest();

        String value = getField(pr, "token", AbstractPaymentPageRequest.class);
        assertEquals("tokenid-1234567890123456", value);
    }

    @Test
    public void setOrderId() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")
                .setOrderId("orderid-1234567890123456")
                .createPaymentRequest();

        String value = getField(pr, "orderId", AbstractPaymentPageRequest.class);
        assertEquals("orderid-1234567890123456", value);

    }



    @Test(expected = IllegalArgumentException.class)
    public void setOrderIdEmptyWhenNoStoreFlag() {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")
                .createPaymentRequest();
    }

    @Test
    public void setOrderIdEmpty() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")
                .setStoreFlag(PaymentRequest.StoreFlag.STORE_ONLY)
                .createPaymentRequest();

        String value = getField(pr, "orderId", AbstractPaymentPageRequest.class);
        assertTrue( value == null || value.equals(""));

    }

    @Test
    public void setOrderDescription() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setOrderDescription("orderDescription-1234567890123456")
                .createPaymentRequest();

        String value = getField(pr, "orderDescription", AbstractPaymentPageRequest.class);
        assertEquals("orderDescription-1234567890123456", value);

    }

    @Test
    public void setOrderDetailDescription() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setOrderDetailDescription("orderDetailDescription-1234567890123456")
                .createPaymentRequest();

        String value = getField(pr, "orderDetailDescription", AbstractPaymentPageRequest.class);
        assertEquals("orderDetailDescription-1234567890123456", value);

    }

    @Test
    public void setAmount() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setAmount(new BigDecimal("123456789012345.0"))
                .createPaymentRequest();

        Double amount = getField(pr, "amount", AbstractPaymentPageRequest.class);
        assertEquals(123456789012345L, amount.longValue());

    }

    @Test
    public void setCurrency() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("USD")
                .createPaymentRequest();

        String value = getField(pr, "currency", AbstractPaymentPageRequest.class);
        assertEquals("USD", value);

    }

    @Test
    public void setVatAmount() {
    }

    @Test
    public void setVatRate() {
    }

    @Test
    public void setConsumerCountry() {
    }

    @Test
    public void setConsumerLanguage() {
    }

    @Test
    public void setReturnUrl() {
    }

    @Test
    public void setTimeLimit() {
    }

    @Test
    public void setAdditionalParameters() {
    }

    @Test
    public void setPaymentMethodId() {
    }

    @Test
    public void setStoreFlag() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setStoreFlag(PaymentRequest.StoreFlag.STORE_ONLY)
                .createPaymentRequest();


        Integer value = getField(pr, "storeFlag", AbstractPaymentPageRequest.class);
        assertEquals(2, (int) value);
    }

    @Test
    public void setBillingAddressLine1() {
    }

    @Test
    public void setBillingAddressLine2() {
    }

    @Test
    public void setBillingCity() {
    }

    @Test
    public void setBillingStateProvince() {
    }

    @Test
    public void setBillingZipCode() {
    }

    @Test
    public void setBillingCountryCode() {
    }

    @Test
    public void setBillingEmailAddress() {
    }

    @Test
    public void setBillingPhone() {
    }

    @Test
    public void setBillingMobilePhone() {
    }

    @Test
    public void setBillingLastName() {
    }

    @Test
    public void setBillingFirstName() {
    }

    @Test
    public void setBillingFullName() {
    }

    @Test
    public void setShippingAddressLine1() {
    }

    @Test
    public void setShippingAddressLine2() {
    }

    @Test
    public void setShippingCity() {
    }

    @Test
    public void setShippingStateProvince() {
    }

    @Test
    public void setShippingZipCode() {
    }

    @Test
    public void setShippingCountryCode() {
    }

    @Test
    public void setShippingEmailAddress() {
    }

    @Test
    public void setShippingPhone() {
    }

    @Test
    public void setDueDate() {
    }

    @Test
    public void setPaymentPlanCode() {
    }

    @Test
    public void setBillingCompanyName() {
    }

    @Test
    public void setBillingBuyerVATNumber() {
    }

    @Test
    public void setBillingBuyerType() {
    }

    @Test
    public void setShippingCompanyName() {
    }

    @Test
    public void setShippingAddressLine3() {
    }

    @Test
    public void setBillingAddressLine3() {
    }

    @Test
    public void setBirthDate() {
    }

    @Test
    public void setCompanyResponsibleBirthDate() {
    }

    @Test
    public void setCompanyResponsibleFullName() {
    }

    @Test
    public void setCompanyResponsibleVATNumber() {
    }

    @Test
    public void setRecurringType() {
    }

    @Test
    public void setPosDesc() {
    }

    @Test
    public void setShippingMobilePhone() {
    }

    @Test
    public void setShippingLastName() {
    }

    @Test
    public void setShippingFirstName() {
    }

    @Test
    public void setShippingFullName() {
    }

    @Test
    public void setBillingSSN() {
    }

    @Test
    public void setCompanyTaxId() {
    }

    @Test
    public void setGender() {
    }

    @Test
    public void setBillingStreetName() {
    }

    @Test
    public void setBillingHouseNumber() {
    }

    @Test
    public void setBillingHouseExtension() {
    }

    @Test
    public void setShippingStreetName() {
    }

    @Test
    public void setShippingHouseNumber() {
    }

    @Test
    public void setShippingHouseExtension() {
    }

    @Test
    public void setShippingCareOf() {
    }

    @Test
    public void setLineItems() {
    }

    @Test
    public void setAuthorizationType() {
    }

    @Test
    public void setAuthenticationRedirect() {
    }

    @Test
    public void setAutoCapture() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setAutoCapture(false)
                .createPaymentRequest();


        Boolean value = getField(pr, "autoCapture", AbstractPaymentPageRequest.class);
        assertFalse(value);

        String value2 = getField(pr, "transactionType", AbstractPaymentPageRequest.class);
        assertEquals("AUTHORIZE", value2);
    }

    @Test
    public void autoCaptureDefault() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .createPaymentRequest();

        Boolean value = getField(pr, "autoCapture", AbstractPaymentPageRequest.class);
        assertTrue(value);

        String value2 = getField(pr, "transactionType", AbstractPaymentPageRequest.class);
        assertEquals("DEBIT", value2);
    }

    @Test
    public void autoCaptureTrue() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setAutoCapture(true)
                .createPaymentRequest();

        Boolean value = getField(pr, "autoCapture", AbstractPaymentPageRequest.class);
        assertTrue(value);

        String value2 = getField(pr, "transactionType", AbstractPaymentPageRequest.class);
        assertEquals("DEBIT", value2);
    }

    @Test
    public void createPaymentRequest() {
    }
    
    @Test
    public void setPaymentMethodIdDefault() throws Exception {
        PaymentRequest pr = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")
                .createPaymentRequest();

        Integer value = getField(pr, "paymentMethodId", AbstractPaymentPageRequest.class);
        assertEquals(1000, (int)value);
    }
}