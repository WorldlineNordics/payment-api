
package com.worldline.payments.api;

public enum AuthorizationType {

	FINAL_AUTHORIZATION, PRE_AUTHORIZATION, UNDEFINED;

	public String value() {
		return name();
	}

	public static AuthorizationType fromValue(String v) {
		return valueOf(v);
	}

}
