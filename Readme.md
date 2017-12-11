# Worldline Online Payments Acceptance - Java Payment API

[ ![Download](https://api.bintray.com/packages/worldlinenordics/payment-api/payment-api/images/download.svg) ](https://bintray.com/worldlinenordics/payment-api/payment-api/_latestVersion)


## Getting started with Device Payment API

```java
        PaymentHandler paymentHandler = new PaymentHandler(
                new JKSKeyHandlerV6("src/test/resources/merchant.jks",
                        "merchant", "merchant", "drwp_cert"),
                "https://worldline-endpoint:1234/");


        PaymentRequest paymentRequest = new PaymentRequestBuilder()
                .setMid(1234567890L)
                .setOrderId("orderid")
                .setAmount(new BigDecimal(100.00))
                .setCurrency("SEK")
                .setConsumerCountry("SE")
                .setConsumerLanguage("sv")
                .createPaymentRequest();

        String deviceAPIRequest = paymentHandler.createDeviceAPIRequest(paymentRequest);

```

## Dependencies

None



