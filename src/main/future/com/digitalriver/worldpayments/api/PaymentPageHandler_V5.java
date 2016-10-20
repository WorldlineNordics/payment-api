package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.security5.KeyHandler;
import com.digitalriver.worldpayments.api.security5.SecurityHandlerImpl;

public class PaymentPageHandler_V5 extends PaymentPageHandler {

	/**
	 * Create a PaymentPageHandler with a specified key handler
	 * 
	 * @param aBaseUrl
	 *            example #DEFAULT_PRODUCTION_BASE_URL
	 * @param aKeyHandler
	 *            a KeyHandler containing payment page keys
	 */
	public PaymentPageHandler_V5(String aBaseUrl, KeyHandler aKeyHandler) {
		super(aBaseUrl, new SecurityHandlerImpl(aKeyHandler));
	}

}
