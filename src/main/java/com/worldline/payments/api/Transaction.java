package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class Transaction {

	@Parameter(shortName = "C")
	protected Long transactionId;
	
	@Parameter(shortName = "AAE")
	protected String transactionDesc;

	public Long getTransactionId() {
		return transactionId;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	
	
}
