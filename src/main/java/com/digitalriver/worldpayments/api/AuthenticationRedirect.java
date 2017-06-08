package com.digitalriver.worldpayments.api;

public enum AuthenticationRedirect {
    NOREDIRECT, REDIRECT, REDIRECTONLY;

    public String value() {
        return name();
    }

    public static AuthenticationRedirect fromValue(String v) {
        return valueOf(v);
    }
}
