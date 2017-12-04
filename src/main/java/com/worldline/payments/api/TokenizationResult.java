package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class TokenizationResult {

	@Parameter(shortName = "N")
	String token;

	@Parameter(shortName = "V")
	String storeMaskedCardNumber;

	@Parameter(shortName = "W")
	String storeExpirationDate;

	/**
	 * @deprecated To reflect that the parameter is really called storeExpirationDate, will be removed in future release, instead use {@link #getStoreExpirationDate()}.
	 * 
	 * @return
	 */
	@Deprecated
	public String getExpirationDate() {
		return getStoreExpirationDate();
	}

	/**
	 * 
	 * @return The expiration date for a card with stored token.
	 */
	public String getStoreExpirationDate() {
		return storeExpirationDate;
	}

	/**
	 * @deprecated To reflect that the parameter is really called
	 *             storeMaskedCardNumber, will be removed in future release,
	 *             instead use {@link #getStoreMaskedCardNumber()}.
	 * 
	 * @return
	 */
	@Deprecated
	public String getMaskedAccountNumber() {
		return getStoreMaskedCardNumber();
	}

	/**
	 * 
	 * @return The masked card number for a card with stored token.
	 */
	public String getStoreMaskedCardNumber() {
		return storeMaskedCardNumber;
	}

	/**
	 * 
	 * @return The token stored for a card number.
	 */
	public String getToken() {
		return token;
	}

}
