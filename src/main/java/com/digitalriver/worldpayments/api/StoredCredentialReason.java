package com.digitalriver.worldpayments.api;

public enum StoredCredentialReason {

	UNSCHEDULED, RECURRING, RESUBMISSION, DELAYED_CHARGES, NO_SHOW, UNDEFINED;

	public String value() {
		return name();
	}

	public static StoredCredentialReason fromValue(String v) {
		return valueOf(v);
	}
}
