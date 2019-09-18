package com.digitalriver.worldpayments.api;

import java.util.Date;

/**
 * Class that contains the response that comes back from PaymentPage when consumer has been redirected back to Merchant
 * 
 * @see PaymentPageHandler
 */
public class PaymentPageResponse extends AbstractPaymentResponse {

    PaymentPageResponse() {
    }

    public Long getMid() {
        return mid;
    }

    public String getOrderId() {
        return orderId;
    }

    /**
     * @return The status of this PaymentPage session OK/NOK/ERROR/USERCANCEL/PENDING
     */
    public String getStatus() {
        return status;
    }

    public String getOriginatingAccountType() {
        return originatingAccountType;
    }

    /**
     * @return The time stamp of when the PaymentPageResponse was created It's created just before the consumer is
     *         redirected back to merchant.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    public TokenizationResult getTokenizationResult() {
        return tokenizationResult;
    }

    /**
     * Transaction created in this PaymentPage session
     * 
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Redirected status, true if the consumer was redirected to a third party site during the session at Payment Page
     * 
     * @return if trhe request was redirected
     */
    public boolean wasRedirected() {
        return redirected.booleanValue();
    }

    /**
     * AVS Answer Code. The response value will only be present if AVS is enabled for the payment type.
     * 
     * @return avs answer code
     */
    public Long getAvsAnswerCode() {
        return avsAnswerCode;
    }

    /**
     * AVS Response. The response value will only be present if AVS is enabled for the payment type.
     * 
     * @return avs response
     */
    public String getAvsResponse() {
        return avsResponse;
    }

    /**
     * Acquirer Answer Code. This is the bank or payment providers answer code.
     * 
     * @return aquierer answer code
     */
    public String getAcquirerAnswerCode() {
        return acquirerAnswerCode;
    }

    /**
     * Client Answer Code. This is the Digital River World Payments internal answer code
     * 
     * @return client answer code
     */
    public Long getClientAnswerCode() {
        return clientAnswerCode;
    }

    /**
     * CVV Answer Code.This answer code will only be present for card payments.
     * 
     * @return cv answer code
     */
    public Long getCvAnswerCode() {
        return cvAnswerCode;
    }

    /**
     * CVV Response. The response value will only be present for card payments.
     * 
     * @return return cv response
     */
    public String getCvResponse() {
        return cvResponse;
    }

    /**
     * @deprecated To reflect that the parameter is really called maskedCardNumber, will be removed in future release,
     *             instead use {@link #getMaskedCardNumber()}.
     * @return
     */
    @Deprecated
    public String getMaskedAccountNumber() {
        return getMaskedCardNumber();
    }

    /**
     * Masked card number. The response value will only be present for card payments.
     * 
     * @return A masked card no.
     */
    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    /**
     * Expiration date for account (card). The response value will only be present for card payments.
     * 
     * @return card expiery date
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Get card type (legacy from ws2006)
     * 
     * @return visa or mastercard for example (unknown case)
     * @deprecated used getPaymentMethodName instead
     */
    @Deprecated
    public String getCardType() {
        return cardType;
    }

    /**
     * Auth code from acquirer (typically card payments)
     * 
     * @return authCode
     */
    public String getAcquirerAuthCode() {
        return acquirerAuthCode;
    }

    /**
     * Name of payment method as returned from payment method config service Visa/Mastercard/Nordea...
     * 
     * @return name
     */
    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public String getHouseExtension() {
        return houseExtension;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public Long getPaymentPlanCode() {
        return PaymentPlanCode;
    }

    public String getSocialSecNumber() {
        return socialSecNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getVResId() {
        return VResId;
    }

    public String getPAResId() {
        return PAResId;
    }

    public String getdddsStatus() {
        return dddsStatus;
    }

    public String getPOSId() {
        return POSId;
    }

    public String getCardTxType() {
        return CardTxType;
    }

    public String getCardTxId() {
        return CardTxId;
    }

    public String getStoreCardType() {
        return StoreCardType;
    }

    public String getIbpTxId() {
        return IbpTxId;
    }

    public String getIbpTxType() {
        return IbpTxType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getEftReferenceId() {
        return eftReferenceId;
    }

    public String getEftPaymentSlipUrl() {
        return eftPaymentSlipUrl;
    }

    public Long getEftTxId() {
        return eftTxId;
    }

    public Long getDirectDebitTxId() {
        return directDebitTxId;
    }

    public Long getPayoutTxId() {
        return payoutTxId;
    }

    public String getSchemeReferenceId() {
        return schemeReferenceId;
    }
    
    public int getEci() {
		return eci;
	}
	
	public String getCavv() {
		return cavv;
	}
	
	public String getXid() {
		return xid;
	}
	
	public String getAuthRequired() {
		return authRequired;
	}
	
	public String getAuthenticationResult() {
		return authenticationResult;
	}
	
	public String getAcsVerificationFlavor() {
		return acsVerificationFlavor;
	}
	
	public String getAuthenticationProtocolVersion() {
		return authenticationProtocolVersion;
	}
	
	public String getAuthenticationDsTransId() {
		return authenticationDsTransId;
	}
	
	public String getAuthenticationApplication() {
		return authenticationApplication;
	}
	
	public String getAuthenticationVerificationData() {
		return authenticationVerificationData;
	}

	public String getMd() {
		return md;
	}

	public String gettDSMethodContent() {
		return tDSMethodContent;
	}

	public String getAcsToken() {
		return acsToken;
	}
    
    public String getAuthenticationStatusDescription() {
        return authenticationStatusDescription;
    }

}