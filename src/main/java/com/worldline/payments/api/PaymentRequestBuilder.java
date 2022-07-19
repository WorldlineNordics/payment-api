package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.AuthenticationRedirect;
import com.digitalriver.worldpayments.api.LineItem;
import com.digitalriver.worldpayments.api.ParameterAnnotationHelper;
import com.digitalriver.worldpayments.api.StoredCredentialIndicator;
import com.digitalriver.worldpayments.api.StoredCredentialReason;
import com.digitalriver.worldpayments.api.ValidationHelper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PaymentRequestBuilder {
    private Long mid;
    private String subMerchantId;
    private String posId = "0";
    private String transactionChannel = "Web Online";
    private String token;
    private String orderId;
    private String orderDescription;
    private String orderDetailDescription;
    private BigDecimal amount = new BigDecimal(0);
    private String currency;
    private BigDecimal vatAmount = new BigDecimal(0);
    private Double vatRate;
    private String consumerCountry;
    private String consumerLanguage;
    private String returnUrl;
    private Integer timeLimit;
    private Map<String, String> additionalParameters;
    private Integer paymentMethodId = 1000;
    private PaymentRequest.StoreFlag storeFlag = PaymentRequest.StoreFlag.NO_STORE;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingCity;
    private String billingStateProvince;
    private String billingZipCode;
    private String billingCountryCode;
    private String billingEmailAddress;
    private String billingPhone;
    private String billingMobilePhone;
    private String billingLastName;
    private String billingFirstName;
    private String billingFullName;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingCity;
    private String shippingStateProvince;
    private String shippingZipCode;
    private String shippingCountryCode;
    private String shippingEmailAddress;
    private String shippingPhone;
    private String dueDate;
    private String paymentPlanCode;
    private String billingCompanyName;
    private String billingBuyerVATNumber;
    private String billingBuyerType;
    private String shippingCompanyName;
    private String shippingAddressLine3;
    private String billingAddressLine3;
    private String birthDate;
    private String companyResponsibleBirthDate;
    private String companyResponsibleFullName;
    private String companyResponsibleVATNumber;
    private String recurringType;
    private String posDesc;
    private String shippingMobilePhone;
    private String shippingLastName;
    private String shippingFirstName;
    private String shippingFullName;
    private String billingSSN;
    private String companyTaxId;
    private String gender;
    private String billingStreetName;
    private String billingHouseNumber;
    private String billingHouseExtension;
    private String shippingStreetName;
    private String shippingHouseNumber;
    private String shippingHouseExtension;
    private String shippingCareOf;
    private List<LineItem> lineItems;
    private AuthorizationType authorizationType;
    private AuthenticationRedirect authenticationRedirect;
    private StoredCredentialIndicator storedCredentialIndicator;
    private StoredCredentialReason storedCredentialReason;
    private String schemeReferenceId;
    private boolean autoCapture = true;
    private Long timestamp = System.currentTimeMillis();
    
	private String purchaseInstallment;
    private String md;
    private String acctID;
    private String acctType;
	private String addrMatch;
    private String messageCategory;
    private String purchaseDate;
    private String transType;
    private String threeRIInd;
    private String threeDSRequestorAuthenticationInd;
    private String threeDSRequestorChallengeInd;
    private String challengeWindowSize;
    private String shipIndicator;
    private String deliveryTimeframe;
    private String deliveryEmailAddress;
    private String reorderItemsInd;
    private String preOrderPurchaseInd;
    private String preOrderDate;
    private String giftCardAmount;
    private String giftCardCurr;
    private String giftCardCount;
    private String chAccAgeInd;
    private String chAccDate;
    private String chAccChangeInd;
    private String chAccChange;
    private String chAccPwChangeInd;
    private String chAccPwChange;
    private String nbPurchaseAccount;
    private String provisionAttemptsDay;
    private String txnActivityDay;
    private String txnActivityYear;
    private String shipAddressUsageInd;
    private String shipAddressUsage;
    private String shipNameIndicator;
    private String paymentAccInd;
    private String paymentAccAge;
    private String suspiciousAccActivity;
    private String threeDSReqAuthMethod;
    private String threeDSReqAuthTimestamp;
    private String threeDSReqPriorRef;
    private String threeDSReqPriorAuthMethod;
    private String threeDSReqPriorAuthTimestamp;
    private String libraryVersion;
    private String sca;
    private String paymentAuthenticationLevel = "Proceed when SCA Successful";

    public PaymentRequestBuilder setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PaymentRequestBuilder setMid(Long mid) {
        this.mid = mid;
        return this;
    }

    public PaymentRequestBuilder setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
        return this;
    }

    public PaymentRequestBuilder setPosId(String posId) {
        this.posId = posId;
        return this;
    }

    public PaymentRequestBuilder setTransactionChannel(String transactionChannel) {
        this.transactionChannel = transactionChannel;
        return this;
    }

    public PaymentRequestBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public PaymentRequestBuilder setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public PaymentRequestBuilder setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public PaymentRequestBuilder setOrderDetailDescription(String orderDetailDescription) {
        this.orderDetailDescription = orderDetailDescription;
        return this;
    }

    public PaymentRequestBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentRequestBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public PaymentRequestBuilder setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
        return this;
    }

    public PaymentRequestBuilder setVatRate(Double vatRate) {
        this.vatRate = vatRate;
        return this;
    }

    public PaymentRequestBuilder setConsumerCountry(String consumerCountry) {
        this.consumerCountry = consumerCountry;
        return this;
    }

    public PaymentRequestBuilder setConsumerLanguage(String consumerLanguage) {
        this.consumerLanguage = consumerLanguage;
        return this;
    }

    public PaymentRequestBuilder setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
        return this;
    }

    public PaymentRequestBuilder setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public PaymentRequestBuilder setAdditionalParameters(Map<String, String> additionalParameters) {
        this.additionalParameters = additionalParameters;
        return this;
    }

    public PaymentRequestBuilder setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
        return this;
    }

    public PaymentRequestBuilder setStoreFlag(PaymentRequest.StoreFlag storeFlag) {
        this.storeFlag = storeFlag;
        return this;
    }

    public PaymentRequestBuilder setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
        return this;
    }

    public PaymentRequestBuilder setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
        return this;
    }

    public PaymentRequestBuilder setBillingCity(String billingCity) {
        this.billingCity = billingCity;
        return this;
    }

    public PaymentRequestBuilder setBillingStateProvince(String billingStateProvince) {
        this.billingStateProvince = billingStateProvince;
        return this;
    }

    public PaymentRequestBuilder setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
        return this;
    }

    public PaymentRequestBuilder setBillingCountryCode(String billingCountryCode) {
        this.billingCountryCode = billingCountryCode;
        return this;
    }

    public PaymentRequestBuilder setBillingEmailAddress(String billingEmailAddress) {
        this.billingEmailAddress = billingEmailAddress;
        return this;
    }

    public PaymentRequestBuilder setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
        return this;
    }

    public PaymentRequestBuilder setBillingMobilePhone(String billingMobilePhone) {
        this.billingMobilePhone = billingMobilePhone;
        return this;
    }

    public PaymentRequestBuilder setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
        return this;
    }

    public PaymentRequestBuilder setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
        return this;
    }

    public PaymentRequestBuilder setBillingFullName(String billingFullName) {
        this.billingFullName = billingFullName;
        return this;
    }

    public PaymentRequestBuilder setShippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
        return this;
    }

    public PaymentRequestBuilder setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
        return this;
    }

    public PaymentRequestBuilder setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
        return this;
    }

    public PaymentRequestBuilder setShippingStateProvince(String shippingStateProvince) {
        this.shippingStateProvince = shippingStateProvince;
        return this;
    }

    public PaymentRequestBuilder setShippingZipCode(String shippingZipCode) {
        this.shippingZipCode = shippingZipCode;
        return this;
    }

    public PaymentRequestBuilder setShippingCountryCode(String shippingCountryCode) {
        this.shippingCountryCode = shippingCountryCode;
        return this;
    }

    public PaymentRequestBuilder setShippingEmailAddress(String shippingEmailAddress) {
        this.shippingEmailAddress = shippingEmailAddress;
        return this;
    }

    public PaymentRequestBuilder setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
        return this;
    }

    public PaymentRequestBuilder setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public PaymentRequestBuilder setPaymentPlanCode(String paymentPlanCode) {
        this.paymentPlanCode = paymentPlanCode;
        return this;
    }

    public PaymentRequestBuilder setBillingCompanyName(String billingCompanyName) {
        this.billingCompanyName = billingCompanyName;
        return this;
    }

    public PaymentRequestBuilder setBillingBuyerVATNumber(String billingBuyerVATNumber) {
        this.billingBuyerVATNumber = billingBuyerVATNumber;
        return this;
    }

    public PaymentRequestBuilder setBillingBuyerType(String billingBuyerType) {
        this.billingBuyerType = billingBuyerType;
        return this;
    }

    public PaymentRequestBuilder setShippingCompanyName(String shippingCompanyName) {
        this.shippingCompanyName = shippingCompanyName;
        return this;
    }

    public PaymentRequestBuilder setShippingAddressLine3(String shippingAddressLine3) {
        this.shippingAddressLine3 = shippingAddressLine3;
        return this;
    }

    public PaymentRequestBuilder setBillingAddressLine3(String billingAddressLine3) {
        this.billingAddressLine3 = billingAddressLine3;
        return this;
    }

    public PaymentRequestBuilder setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PaymentRequestBuilder setCompanyResponsibleBirthDate(String companyResponsibleBirthDate) {
        this.companyResponsibleBirthDate = companyResponsibleBirthDate;
        return this;
    }

    public PaymentRequestBuilder setCompanyResponsibleFullName(String companyResponsibleFullName) {
        this.companyResponsibleFullName = companyResponsibleFullName;
        return this;
    }

    public PaymentRequestBuilder setCompanyResponsibleVATNumber(String companyResponsibleVATNumber) {
        this.companyResponsibleVATNumber = companyResponsibleVATNumber;
        return this;
    }

    public PaymentRequestBuilder setRecurringType(String recurringType) {
        this.recurringType = recurringType;
        return this;
    }

    public PaymentRequestBuilder setPosDesc(String posDesc) {
        this.posDesc = posDesc;
        return this;
    }

    public PaymentRequestBuilder setShippingMobilePhone(String shippingMobilePhone) {
        this.shippingMobilePhone = shippingMobilePhone;
        return this;
    }

    public PaymentRequestBuilder setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
        return this;
    }

    public PaymentRequestBuilder setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
        return this;
    }

    public PaymentRequestBuilder setShippingFullName(String shippingFullName) {
        this.shippingFullName = shippingFullName;
        return this;
    }

    public PaymentRequestBuilder setBillingSSN(String billingSSN) {
        this.billingSSN = billingSSN;
        return this;
    }

    public PaymentRequestBuilder setCompanyTaxId(String companyTaxId) {
        this.companyTaxId = companyTaxId;
        return this;
    }

    public PaymentRequestBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PaymentRequestBuilder setBillingStreetName(String billingStreetName) {
        this.billingStreetName = billingStreetName;
        return this;
    }

    public PaymentRequestBuilder setBillingHouseNumber(String billingHouseNumber) {
        this.billingHouseNumber = billingHouseNumber;
        return this;
    }

    public PaymentRequestBuilder setBillingHouseExtension(String billingHouseExtension) {
        this.billingHouseExtension = billingHouseExtension;
        return this;
    }

    public PaymentRequestBuilder setShippingStreetName(String shippingStreetName) {
        this.shippingStreetName = shippingStreetName;
        return this;
    }

    public PaymentRequestBuilder setShippingHouseNumber(String shippingHouseNumber) {
        this.shippingHouseNumber = shippingHouseNumber;
        return this;
    }

    public PaymentRequestBuilder setShippingHouseExtension(String shippingHouseExtension) {
        this.shippingHouseExtension = shippingHouseExtension;
        return this;
    }

    public PaymentRequestBuilder setShippingCareOf(String shippingCareOf) {
        this.shippingCareOf = shippingCareOf;
        return this;
    }

    public PaymentRequestBuilder setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    public PaymentRequestBuilder setAuthorizationType(AuthorizationType authorizationType) {
        this.authorizationType = authorizationType;
        return this;
    }

    public PaymentRequestBuilder setAuthenticationRedirect(AuthenticationRedirect authenticationRedirect) {
        this.authenticationRedirect = authenticationRedirect;
        return this;
    }

    public PaymentRequestBuilder setAutoCapture(boolean autoCapture) {
        this.autoCapture = autoCapture;
        return this;
    }
    
    public PaymentRequestBuilder setStoredCredentialIndicator(StoredCredentialIndicator storedCredentialIndicator) {
		this.storedCredentialIndicator = storedCredentialIndicator;
		return this;
	}

	public PaymentRequestBuilder setStoredCredentialReason(StoredCredentialReason storedCredentialReason) {
		this.storedCredentialReason = storedCredentialReason;
		return this;
	}

	public PaymentRequestBuilder setSchemeReferenceId(String schemeReferenceId) {
		this.schemeReferenceId = schemeReferenceId;
		return this;
	}
	
	public PaymentRequestBuilder setPurchaseInstallment(String purchaseInstallment) {
		this.purchaseInstallment = purchaseInstallment;
		return this;
	}

	public PaymentRequestBuilder setMd(String md) {
		this.md = md;
		return this;
	}

	public PaymentRequestBuilder setAcctID(String acctID) {
		this.acctID = acctID;
		return this;
	}

	public PaymentRequestBuilder setAcctType(String acctType) {
		this.acctType = acctType;
		return this;
	}

	public PaymentRequestBuilder setAddrMatch(String addrMatch) {
		this.addrMatch = addrMatch;
		return this;
	}

	public PaymentRequestBuilder setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
		return this;
	}

	public PaymentRequestBuilder setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
		return this;
	}

	public PaymentRequestBuilder setTransType(String transType) {
		this.transType = transType;
		return this;
	}

	public PaymentRequestBuilder setThreeRIInd(String threeRIInd) {
		this.threeRIInd = threeRIInd;
		return this;
	}

	public PaymentRequestBuilder setThreeDSRequestorAuthenticationInd(String threeDSRequestorAuthenticationInd) {
		this.threeDSRequestorAuthenticationInd = threeDSRequestorAuthenticationInd;
		return this;
	}

	public PaymentRequestBuilder setThreeDSRequestorChallengeInd(String threeDSRequestorChallengeInd) {
		this.threeDSRequestorChallengeInd = threeDSRequestorChallengeInd;
		return this;
	}

	public PaymentRequestBuilder setChallengeWindowSize(String challengeWindowSize) {
		this.challengeWindowSize = challengeWindowSize;
		return this;
	}

	public PaymentRequestBuilder setShipIndicator(String shipIndicator) {
		this.shipIndicator = shipIndicator;
		return this;
	}

	public PaymentRequestBuilder setDeliveryTimeframe(String deliveryTimeframe) {
		this.deliveryTimeframe = deliveryTimeframe;
		return this;
	}

	public PaymentRequestBuilder setDeliveryEmailAddress(String deliveryEmailAddress) {
		this.deliveryEmailAddress = deliveryEmailAddress;
		return this;
	}

	public PaymentRequestBuilder setReorderItemsInd(String reorderItemsInd) {
		this.reorderItemsInd = reorderItemsInd;
		return this;
	}

	public PaymentRequestBuilder setPreOrderPurchaseInd(String preOrderPurchaseInd) {
		this.preOrderPurchaseInd = preOrderPurchaseInd;
		return this;
	}

	public PaymentRequestBuilder setPreOrderDate(String preOrderDate) {
		this.preOrderDate = preOrderDate;
		return this;
	}

	public PaymentRequestBuilder setGiftCardAmount(String giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
		return this;
	}

	public PaymentRequestBuilder setGiftCardCurr(String giftCardCurr) {
		this.giftCardCurr = giftCardCurr;
		return this;
	}

	public PaymentRequestBuilder setGiftCardCount(String giftCardCount) {
		this.giftCardCount = giftCardCount;
		return this;
	}

	public PaymentRequestBuilder setChAccAgeInd(String chAccAgeInd) {
		this.chAccAgeInd = chAccAgeInd;
		return this;
	}

	public PaymentRequestBuilder setChAccDate(String chAccDate) {
		this.chAccDate = chAccDate;
		return this;
	}

	public PaymentRequestBuilder setChAccChangeInd(String chAccChangeInd) {
		this.chAccChangeInd = chAccChangeInd;
		return this;
	}

	public PaymentRequestBuilder setChAccChange(String chAccChange) {
		this.chAccChange = chAccChange;
		return this;
	}

	public PaymentRequestBuilder setChAccPwChangeInd(String chAccPwChangeInd) {
		this.chAccPwChangeInd = chAccPwChangeInd;
		return this;
	}

	public PaymentRequestBuilder setChAccPwChange(String chAccPwChange) {
		this.chAccPwChange = chAccPwChange;
		return this;
	}

	public PaymentRequestBuilder setNbPurchaseAccount(String nbPurchaseAccount) {
		this.nbPurchaseAccount = nbPurchaseAccount;
		return this;
	}

	public PaymentRequestBuilder setProvisionAttemptsDay(String provisionAttemptsDay) {
		this.provisionAttemptsDay = provisionAttemptsDay;
		return this;
	}

	public PaymentRequestBuilder setTxnActivityDay(String txnActivityDay) {
		this.txnActivityDay = txnActivityDay;
		return this;
	}

	public PaymentRequestBuilder setTxnActivityYear(String txnActivityYear) {
		this.txnActivityYear = txnActivityYear;
		return this;
	}

	public PaymentRequestBuilder setShipAddressUsageInd(String shipAddressUsageInd) {
		this.shipAddressUsageInd = shipAddressUsageInd;
		return this;
	}

	public PaymentRequestBuilder setShipAddressUsage(String shipAddressUsage) {
		this.shipAddressUsage = shipAddressUsage;
		return this;
	}

	public PaymentRequestBuilder setShipNameIndicator(String shipNameIndicator) {
		this.shipNameIndicator = shipNameIndicator;
		return this;
	}

	public PaymentRequestBuilder setPaymentAccInd(String paymentAccInd) {
		this.paymentAccInd = paymentAccInd;
		return this;
	}

	public PaymentRequestBuilder setPaymentAccAge(String paymentAccAge) {
		this.paymentAccAge = paymentAccAge;
		return this;
	}

	public PaymentRequestBuilder setSuspiciousAccActivity(String suspiciousAccActivity) {
		this.suspiciousAccActivity = suspiciousAccActivity;
		return this;
	}

	public PaymentRequestBuilder setThreeDSReqAuthMethod(String threeDSReqAuthMethod) {
		this.threeDSReqAuthMethod = threeDSReqAuthMethod;
		return this;
	}

	public PaymentRequestBuilder setThreeDSReqAuthTimestamp(String threeDSReqAuthTimestamp) {
		this.threeDSReqAuthTimestamp = threeDSReqAuthTimestamp;
		return this;
	}

	public PaymentRequestBuilder setThreeDSReqPriorRef(String threeDSReqPriorRef) {
		this.threeDSReqPriorRef = threeDSReqPriorRef;
		return this;
	}

	public PaymentRequestBuilder setThreeDSReqPriorAuthMethod(String threeDSReqPriorAuthMethod) {
		this.threeDSReqPriorAuthMethod = threeDSReqPriorAuthMethod;
		return this;
	}

	public PaymentRequestBuilder setThreeDSReqPriorAuthTimestamp(String threeDSReqPriorAuthTimestamp) {
		this.threeDSReqPriorAuthTimestamp = threeDSReqPriorAuthTimestamp;
		return this;
	}
	
	public PaymentRequestBuilder setSca(String sca) {
		this.sca = sca;
		return this;
	}
	
	public PaymentRequestBuilder setPaymentAuthenticationLevel(String paymentAuthenticationLevel) {
		this.paymentAuthenticationLevel = paymentAuthenticationLevel;
		return this;
	}

	public String getLibraryVersion() {
		this.libraryVersion = PaymentRequestBuilder.class.getPackage().getImplementationVersion();
		if(this.libraryVersion == null) {
			libraryVersion = "";
		}
		return libraryVersion;
	}

	public PaymentRequest createPaymentRequest() throws IllegalArgumentException {
        PaymentRequest paymentRequest = new PaymentRequest(mid, subMerchantId, posId, transactionChannel, token, orderId, orderDescription, orderDetailDescription, amount, currency, vatAmount, vatRate, consumerCountry, consumerLanguage, returnUrl, timeLimit, additionalParameters, paymentMethodId, storeFlag, billingAddressLine1, billingAddressLine2, billingCity, billingStateProvince, billingZipCode, billingCountryCode, billingEmailAddress, billingPhone, billingMobilePhone, billingLastName, billingFirstName, billingFullName, shippingAddressLine1, shippingAddressLine2, shippingCity, shippingStateProvince, shippingZipCode, shippingCountryCode, shippingEmailAddress, shippingPhone, dueDate, paymentPlanCode, billingCompanyName, billingBuyerVATNumber, billingBuyerType, shippingCompanyName, shippingAddressLine3, billingAddressLine3, birthDate, companyResponsibleBirthDate, companyResponsibleFullName, companyResponsibleVATNumber, recurringType, posDesc, shippingMobilePhone, shippingLastName, shippingFirstName, shippingFullName, billingSSN, companyTaxId, gender, billingStreetName, billingHouseNumber, billingHouseExtension, shippingStreetName, shippingHouseNumber, shippingHouseExtension, shippingCareOf, lineItems, authorizationType, authenticationRedirect, autoCapture, timestamp, storedCredentialIndicator, storedCredentialReason, schemeReferenceId,
        		purchaseInstallment, md, acctID, acctType, addrMatch, messageCategory, purchaseDate, transType, threeRIInd, threeDSRequestorAuthenticationInd, threeDSRequestorChallengeInd, challengeWindowSize, shipIndicator, deliveryTimeframe, deliveryEmailAddress, reorderItemsInd, preOrderPurchaseInd, preOrderDate, giftCardAmount, giftCardCurr, giftCardCount, chAccAgeInd, chAccDate, chAccChangeInd, chAccChange, chAccPwChangeInd, chAccPwChange, nbPurchaseAccount, provisionAttemptsDay, txnActivityDay, txnActivityYear, shipAddressUsageInd, shipAddressUsage, shipNameIndicator, paymentAccInd, paymentAccAge, suspiciousAccActivity, threeDSReqAuthMethod, threeDSReqAuthTimestamp, threeDSReqPriorRef, threeDSReqPriorAuthMethod, threeDSReqPriorAuthTimestamp, sca, getLibraryVersion(), paymentAuthenticationLevel);

        //FIXME: This creates an unnecessary nvp. Needed for validation purposes only
        ParameterAnnotationHelper.mapObjectToNvp(paymentRequest);

        ValidationHelper.validateNonAnnotatedFields(paymentRequest);


        return paymentRequest;
    }
}