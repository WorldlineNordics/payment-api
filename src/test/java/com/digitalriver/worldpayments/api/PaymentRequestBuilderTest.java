package com.digitalriver.worldpayments.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PaymentRequestBuilderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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

        assertEquals(2234567890L, (long) pr.mid);
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

        assertEquals("Test", pr.subMerchantId);
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

        assertEquals("Test", pr.posId);
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

        assertEquals("0", pr.posId);
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

        assertEquals("Fax", pr.transactionChannel);

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

        assertEquals("Web Online", pr.transactionChannel);
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

        assertEquals("tokenid-1234567890123456", pr.token);
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

        assertEquals("orderid-1234567890123456", pr.orderId);
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

        assertEquals("orderDescription-1234567890123456", pr.orderDescription);
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

        assertEquals("orderDetailDescription-1234567890123456", pr.orderDetailDescription);
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

        assertEquals(123456789012345L, pr.amount.longValue());
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

        assertEquals("USD", pr.currency);
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

        assertEquals(2, (long)pr.storeFlag);

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

        assertFalse(pr.autoCapture);
        assertEquals("AUTHORIZE", pr.transactionType);
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

        assertTrue(pr.autoCapture);
        assertEquals("DEBIT", pr.transactionType);
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

        assertTrue("Requested Auto Capture", pr.autoCapture);
        assertEquals("In background, this becomes a DEBIT request", "DEBIT", pr.transactionType);
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

        assertEquals(new Integer(1000), pr.paymentMethodId);
    }
}