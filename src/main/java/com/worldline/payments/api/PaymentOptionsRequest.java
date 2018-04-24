package com.worldline.payments.api;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class PaymentOptionsRequest {

	private String referenceId;
	private Long merchantId;
	private String posId;
	
	public PaymentOptionsRequest(final String referenceId, final Long merchantId, final String posId) {
		super();
		this.referenceId = referenceId;
		this.merchantId = merchantId;
		this.posId = posId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	
	public String getPosId() {
		return posId;
	}

	@Override
	public String toString() {
		return "PaymentOptionsRequest [referenceId=" + referenceId + ", merchantId=" + merchantId + ", posId=" + posId + "]";
	}

	public static Map<String, String> mapObjectToNvp(PaymentOptionsRequest request) {
		Map<String, String> nvp = new HashMap<String, String>();
		nvp.put("referenceId", request.getReferenceId());
		nvp.put("merchantId", Long.toString(request.getMerchantId()));
		nvp.put("posId", request.getPosId());
		return nvp;
	}

}
