package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class FraudResult {

    @Parameter(shortName = "AAO")
    protected String status;

    @Parameter(shortName = "AAP")
    protected String code;

    @Parameter(shortName = "AAQ")
    protected String message;
}
