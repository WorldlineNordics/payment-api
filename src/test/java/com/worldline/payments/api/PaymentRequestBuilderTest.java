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
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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
    public void setWrongMid() throws Exception {
        try {
            new PaymentRequestBuilder()
                    .setMid(123456L)
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
                .setOrderId("general-test")
                .setConsumerCountry("UK")
                .setConsumerLanguage("en")
                .setCurrency("GBP")

                .setOrderId("orderid-1234567890123456")
                .createPaymentRequest();

        String value = getField(pr, "orderId", AbstractPaymentPageRequest.class);
        assertEquals("orderid-1234567890123456", value);

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
    public void setVatAmount() throws Exception {
    }

    @Test
    public void setVatRate() throws Exception {
    }

    @Test
    public void setConsumerCountry() throws Exception {
    }

    @Test
    public void setConsumerLanguage() throws Exception {
    }

    @Test
    public void setReturnUrl() throws Exception {
    }

    @Test
    public void setTimeLimit() throws Exception {
    }

    @Test
    public void setAdditionalParameters() throws Exception {
    }

    @Test
    public void setPaymentMethodId() throws Exception {
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
    public void setBillingAddressLine1() throws Exception {
    }

    @Test
    public void setBillingAddressLine2() throws Exception {
    }

    @Test
    public void setBillingCity() throws Exception {
    }

    @Test
    public void setBillingStateProvince() throws Exception {
    }

    @Test
    public void setBillingZipCode() throws Exception {
    }

    @Test
    public void setBillingCountryCode() throws Exception {
    }

    @Test
    public void setBillingEmailAddress() throws Exception {
    }

    @Test
    public void setBillingPhone() throws Exception {
    }

    @Test
    public void setBillingMobilePhone() throws Exception {
    }

    @Test
    public void setBillingLastName() throws Exception {
    }

    @Test
    public void setBillingFirstName() throws Exception {
    }

    @Test
    public void setBillingFullName() throws Exception {
    }

    @Test
    public void setShippingAddressLine1() throws Exception {
    }

    @Test
    public void setShippingAddressLine2() throws Exception {
    }

    @Test
    public void setShippingCity() throws Exception {
    }

    @Test
    public void setShippingStateProvince() throws Exception {
    }

    @Test
    public void setShippingZipCode() throws Exception {
    }

    @Test
    public void setShippingCountryCode() throws Exception {
    }

    @Test
    public void setShippingEmailAddress() throws Exception {
    }

    @Test
    public void setShippingPhone() throws Exception {
    }

    @Test
    public void setDueDate() throws Exception {
    }

    @Test
    public void setPaymentPlanCode() throws Exception {
    }

    @Test
    public void setBillingCompanyName() throws Exception {
    }

    @Test
    public void setBillingBuyerVATNumber() throws Exception {
    }

    @Test
    public void setBillingBuyerType() throws Exception {
    }

    @Test
    public void setShippingCompanyName() throws Exception {
    }

    @Test
    public void setShippingAddressLine3() throws Exception {
    }

    @Test
    public void setBillingAddressLine3() throws Exception {
    }

    @Test
    public void setBirthDate() throws Exception {
    }

    @Test
    public void setCompanyResponsibleBirthDate() throws Exception {
    }

    @Test
    public void setCompanyResponsibleFullName() throws Exception {
    }

    @Test
    public void setCompanyResponsibleVATNumber() throws Exception {
    }

    @Test
    public void setRecurringType() throws Exception {
    }

    @Test
    public void setPosDesc() throws Exception {
    }

    @Test
    public void setShippingMobilePhone() throws Exception {
    }

    @Test
    public void setShippingLastName() throws Exception {
    }

    @Test
    public void setShippingFirstName() throws Exception {
    }

    @Test
    public void setShippingFullName() throws Exception {
    }

    @Test
    public void setBillingSSN() throws Exception {
    }

    @Test
    public void setCompanyTaxId() throws Exception {
    }

    @Test
    public void setGender() throws Exception {
    }

    @Test
    public void setBillingStreetName() throws Exception {
    }

    @Test
    public void setBillingHouseNumber() throws Exception {
    }

    @Test
    public void setBillingHouseExtension() throws Exception {
    }

    @Test
    public void setShippingStreetName() throws Exception {
    }

    @Test
    public void setShippingHouseNumber() throws Exception {
    }

    @Test
    public void setShippingHouseExtension() throws Exception {
    }

    @Test
    public void setShippingCareOf() throws Exception {
    }

    @Test
    public void setLineItems() throws Exception {
    }

    @Test
    public void setAuthorizationType() throws Exception {
    }

    @Test
    public void setAuthenticationRedirect() throws Exception {
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
    public void createPaymentRequest() throws Exception {
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