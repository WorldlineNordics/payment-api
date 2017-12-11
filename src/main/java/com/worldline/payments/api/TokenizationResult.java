package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class TokenizationResult {

	@Parameter(shortName = "N")
    protected String token;
    @Parameter(shortName = "V")
    protected String storeMaskedCardNumber;
    @Parameter(shortName = "W")
    protected String storeExpirationDate;

	/**
     *
     * @return The expiration date for a card with stored token.
     */
    public String getStoreExpirationDate() {
        return storeExpirationDate;
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
