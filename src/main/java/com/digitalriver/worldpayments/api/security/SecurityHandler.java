package com.digitalriver.worldpayments.api.security;


public interface SecurityHandler {

	public abstract String decrypt(String aRedirect);

	public abstract String encrypt(String aRedirect)
			throws SecurityHandlerException;

}