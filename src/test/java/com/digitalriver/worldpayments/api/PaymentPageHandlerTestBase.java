package com.digitalriver.worldpayments.api;

import java.io.File;

import org.junit.BeforeClass;

import com.digitalriver.worldpayments.api.security5.DERKeyHandler;

public abstract class PaymentPageHandlerTestBase {

    static File derFile;
    static PaymentPageHandler_V5 paymentPageHandler;

    @BeforeClass
    public static void setUp() {
        derFile = new File(PaymentPageHandlerTestBase.class.getResource("/merchant_key.der").getFile());
        paymentPageHandler = new PaymentPageHandler_V5(new DERKeyHandler(derFile));
    }

    protected PaymentPageRequest createValidRequest() {
        PaymentPageRequest request = new PaymentPageRequest();
        request.setMid(123456789L);
        request.setSubMerchantId("1");
        request.setPosId("SE");
        request.setAmount(100.00);
        request.setTransactionChannel("Web Online");
        request.setOrderId("OrderId");
        request.setCurrency("SEK");
        request.setConsumerCountry("SE");
        request.setConsumerLanguage("sv");
        request.setReturnUrl("http://merchant.com");
        return request;
    }
}
