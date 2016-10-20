package com.digitalriver.worldpayments.api.security;

public class SecurityHandlerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SecurityHandlerException(String aMessage) {
		super(aMessage);
	}

	public SecurityHandlerException(String aMessage, Throwable aCause) {
		super(aMessage, aCause);
	}
}
