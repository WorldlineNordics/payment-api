package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class Transaction {

	@Parameter(shortName = "C")
	Long transactionId;

	/**
	 * @deprecated The parameter is really called transactionId, this method
	 *             will be removed in a future release, please use
	 *             {@link #getTransactionId()} instead.
	 * @return
	 */
	@Deprecated
	public Long getId() {
		return getTransactionId();
	}

	/**
	 * 
	 * @return The transaction id.
	 */
	public Long getTransactionId() {
		return transactionId;
	}
}
