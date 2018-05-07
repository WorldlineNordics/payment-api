package com.worldline.payments.api;

import java.util.HashMap;
import java.util.Map;

public class PaymentOptionsRequest {

	private String storedUserReference;
	private Long merchantId;
	private String posId;
	
	public PaymentOptionsRequest(String storedUserReference, Long merchantId, String posId) {
		super();
		this.storedUserReference = storedUserReference;
		this.merchantId = merchantId;
		this.posId = posId;
	}

	public String getStoredUserReference() {
		return storedUserReference;
	}


	public Long getMerchantId() {
		return merchantId;
	}

	
	public String getPosId() {
		return posId;
	}

	@Override
	public String toString() {
		return "PaymentOptionsRequest [referenceId=" + storedUserReference + ", merchantId=" + merchantId + ", posId=" + posId + "]";
	}

	public static Map<String, String> mapObjectToNvp(PaymentOptionsRequest request) {
		Map<String, String> nvp = new HashMap<String, String>();
		nvp.put("referenceId", request.getStoredUserReference());
		nvp.put("merchantId", Long.toString(request.getMerchantId()));
		nvp.put("posId", request.getPosId());
		return nvp;
	}

}
