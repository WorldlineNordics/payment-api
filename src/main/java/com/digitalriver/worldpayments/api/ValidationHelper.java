package com.digitalriver.worldpayments.api;


public class ValidationHelper {

    public static void validateNonAnnotatedFields(AbstractPaymentPageRequest request) {
        orderIdMayBeEmpty(request);
    }

    // We might want to change the annotation based validation to something advanced,
    // conditional, based on another field that has a value, but this is OK.
    private static void orderIdMayBeEmpty(AbstractPaymentPageRequest request) {
        final String orderId = request.orderId;
        final Integer storeFlag = request.storeFlag;
        if ((orderId == null || orderId.isEmpty())) {
            if ((storeFlag == null || storeFlag != PaymentPageRequest.StoreFlag.STORE_ONLY.getVal()))
                throw new IllegalArgumentException("OrderId can only be empty for Tokenization requests.");
        }
    }
}
