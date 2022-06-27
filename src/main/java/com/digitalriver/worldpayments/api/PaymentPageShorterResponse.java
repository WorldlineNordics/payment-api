package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class PaymentPageShorterResponse {

	@Parameter(shortName = "ACA")
    protected String orderId;
	
	@Parameter(shortName = "ACB")
    protected String transactionStatus;
	
	@Parameter(shortName = "ACC")
    protected String transactionType;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	
	
}
