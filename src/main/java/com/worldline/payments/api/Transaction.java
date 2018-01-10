package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class Transaction {

    @Parameter(shortName = "C")
    protected Long transactionId;

    @Parameter(shortName = "AAE")
    protected String transactionDesc;

    @Parameter(shortName = "AAF")
    protected String transactionState;

    @Parameter(shortName = "AAG")
    protected String transactionType;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}
