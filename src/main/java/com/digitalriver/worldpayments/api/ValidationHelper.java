package com.digitalriver.worldpayments.api;


public class ValidationHelper {

    public static void validateNonAnnotatedFields(AbstractPaymentPageRequest request) {
        orderIdMayBeEmpty(request);
    }

    private static void orderIdMayBeEmpty(AbstractPaymentPageRequest request) {
        final String orderId = request.orderId;
        final Integer storeFlag = request.storeFlag;
        if ((orderId == null || orderId.isEmpty())) {
            if ((storeFlag == null || storeFlag != PaymentPageRequest.StoreFlag.STORE_ONLY.getVal()))
                throw new IllegalArgumentException("OrderId can only be empty for Tokenization requests.");
        }
    }
}
