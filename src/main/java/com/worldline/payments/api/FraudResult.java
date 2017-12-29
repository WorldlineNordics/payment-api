package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class FraudResult {

    @Parameter(shortName = "AAP")
    protected String status;

    @Parameter(shortName = "AAQ")
    protected String code;

    @Parameter(shortName = "AAR")
    protected String message;
}
