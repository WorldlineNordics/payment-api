package com.worldline.payments.api;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Response from Worldline endpoint.
 *
 * @see PaymentHandler
 */
public class PaymentResponse extends AbstractPaymentResponse {

    PaymentResponse() {
    }
    
    public Long getMid() {
        return mid;
    }

    public String getOrderId() {
        return orderId;
    }

    /**
     * @return The status of this Payment session OK/NOK/ERROR/USERCANCEL/PENDING
     */
    public String getStatus() {
        if (status == null || status.isEmpty()) {
            if (clientAnswerCode == 0) {
                return status = "OK";
            } else {
                return status = "NOK";
            }
        } else {
            return status;
        }
    }

    /**
     * @return The time stamp of when the PaymentResponse was created It's created just before the consumer is
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
     * Auth code from acquirer (typically card payments)
     * 
     * @return authCode
     */
    public String getAcquirerAuthCode() {
        return acquirerAuthCode;
    }

    /**
     * Name of payment method as returned from lookup at Worldline. Visa/Mastercard/Nordea.
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public BigDecimal getFulfilmentAmount() {
        return fulfilmentAmount;
    }

    public BigDecimal getCapturedAmount() {
        return capturedAmount;
    }

    public BigDecimal getRefundedAmount() {
        return refundedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isCapturable() {
        return capturable;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public FraudResult getFraudResult() {
        return fraudResult;
    }

    public String getSubMerchantId() {
        return subMerchantId;
    }

    public Long getRefTransactionId() {
        return refTransactionId;
    }

    public String getAcquirerReferenceId() {
        return acquirerReferenceId;
    }

    public String getPosDescription() {
        return posDescription;
    }


    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getRedirectParameters() {
        return redirectParameters;
    }

    public String getRedirectMethod() {
        return redirectMethod;
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
	
}