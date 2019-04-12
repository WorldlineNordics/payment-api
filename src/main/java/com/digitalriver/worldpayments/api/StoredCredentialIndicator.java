package com.digitalriver.worldpayments.api;

public enum StoredCredentialIndicator {

	CIT_FIRST_TIME, CIT_USE_STORED, MIT_USE_STORED;

	public String value() {
		return name();
	}

	public static StoredCredentialIndicator fromValue(String v) {
		return valueOf(v);
	}
}
