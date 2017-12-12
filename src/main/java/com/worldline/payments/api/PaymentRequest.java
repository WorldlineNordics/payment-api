package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.AbstractPaymentPageRequest;
import com.digitalriver.worldpayments.api.AuthenticationRedirect;
import com.digitalriver.worldpayments.api.LineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PaymentRequest extends AbstractPaymentPageRequest {

    public PaymentRequest(Long mid, String subMerchantId, String posId, String transactionChannel, String token, String orderId, String orderDescription, String orderDetailDescription, BigDecimal amount, String currency, BigDecimal vatAmount, Double vatRate, String consumerCountry, String consumerLanguage, String returnUrl, Integer timeLimit, Map<String, String> additionalParameters, Integer paymentMethodId, StoreFlag storeFlag, String billingAddressLine1, String billingAddressLine2, String billingCity, String billingStateProvince, String billingZipCode, String billingCountryCode, String billingEmailAddress, String billingPhone, String billingMobilePhone, String billingLastName, String billingFirstName, String billingFullName, String shippingAddressLine1, String shippingAddressLine2, String shippingCity, String shippingStateProvince, String shippingZipCode, String shippingCountryCode, String shippingEmailAddress, String shippingPhone, String dueDate, String paymentPlanCode, String billingCompanyName, String billingBuyerVATNumber, String billingBuyerType, String shippingCompanyName, String shippingAddressLine3, String billingAddressLine3, String birthDate, String companyResponsibleBirthDate, String companyResponsibleFullName, String companyResponsibleVATNumber, String recurringType, String posDesc, String shippingMobilePhone, String shippingLastName, String shippingFirstName, String shippingFullName, String billingSSN, String companyTaxId, String gender, String billingStreetName, String billingHouseNumber, String billingHouseExtension, String shippingStreetName, String shippingHouseNumber, String shippingHouseExtension, String shippingCareOf, List<LineItem> lineItems, AuthorizationType authorizationType, AuthenticationRedirect authenticationRedirect, boolean autoCapture, Long timestamp) {
        setMid(mid);
        setSubMerchantId(subMerchantId);
        setPosId(posId);
        setTransactionChannel(transactionChannel);
        setTransactionType(transactionType);
        setToken(token);
        setOrderId(orderId);
        setOrderDescription(orderDescription);
        setOrderDetailDescription(orderDetailDescription);
        setAmount(amount);
        setCurrency(currency);
        setVATRate(vatRate);
        setConsumerCountry(consumerCountry);
        setConsumerLanguage(consumerLanguage);
        setReturnUrl(returnUrl);
        setTimeLimit(timeLimit);
        setAdditionalParameters(additionalParameters);
        setPaymentMethodId(paymentMethodId);
        setStoreFlag(storeFlag);
        setBillingAddressLine1(billingAddressLine1);
        setBillingAddressLine2(billingAddressLine2);
        setBillingCity(billingCity);
        setBillingStateProvince(billingStateProvince);
        setBillingZipCode(billingZipCode);
        setBillingCountryCode(billingCountryCode);
        setBillingEmailAddress(billingEmailAddress);
        setBillingPhone(billingPhone);
        setBillingMobilePhone(billingMobilePhone);
        setBillingLastName(billingLastName);
        setBillingFirstName(billingFirstName);
        setBillingFullName(billingFullName);
        setShippingAddressLine1(shippingAddressLine1);
        setShippingAddressLine2(shippingAddressLine2);
        setShippingCity(shippingCity);
        setShippingStateProvince(shippingStateProvince);
        setShippingZipCode(shippingZipCode);
        setShippingCountryCode(shippingCountryCode);
        setShippingEmailAddress(shippingEmailAddress);
        setShippingPhone(shippingPhone);
        setDueDate(dueDate);
        setPaymentPlanCode(paymentPlanCode);
        setBillingCompanyName(billingCompanyName);
        setBillingBuyerVatNumber(billingBuyerVATNumber);
        setBillingBuyerType(billingBuyerType);
        setShippingCompanyName(shippingCompanyName);
        setShippingAddressLine3(shippingAddressLine3);
        setBillingAddressLine3(billingAddressLine3);
        setBirthDate(birthDate);
        setCompanyResponsibleBirthDate(companyResponsibleBirthDate);
        setCompanyResponsibleFullName(companyResponsibleFullName);
        setCompanyResponsibleVatNumber(companyResponsibleVATNumber);
        setRecurringType(recurringType);
        setPosDesc(posDesc);
        setShippingMobilePhone(shippingMobilePhone);
        setShippingLastName(shippingLastName);

        setBillingFirstName(billingFirstName);
        setBillingFullName(billingFullName);
        setShippingAddressLine1(shippingAddressLine1);
        setShippingAddressLine2(shippingAddressLine2);
        setShippingCity(shippingCity);
        setShippingStateProvince(shippingStateProvince);
        setShippingZipCode(shippingZipCode);
        setShippingCountryCode(shippingCountryCode);
        setShippingEmailAddress(shippingEmailAddress);
        setShippingPhone(shippingPhone);
        setDueDate(dueDate);
        setPaymentPlanCode(paymentPlanCode);
        setBillingCompanyName(billingCompanyName);
        setBillingBuyerVatNumber(billingBuyerVATNumber);
        setBillingBuyerType(billingBuyerType);
        setShippingCompanyName(shippingCompanyName);
        setShippingAddressLine3(shippingAddressLine3);
        setBillingAddressLine3(billingAddressLine3);
        setBirthDate(birthDate);
        setCompanyResponsibleBirthDate(companyResponsibleBirthDate);
        setCompanyResponsibleFullName(companyResponsibleFullName);
        setCompanyResponsibleVatNumber(companyResponsibleVATNumber);
        setRecurringType(recurringType);
        setPosDesc(posDesc);
        setShippingMobilePhone(shippingMobilePhone);
        setShippingLastName(shippingLastName);
        setShippingFirstName(shippingFirstName);
        setShippingFullName(shippingFullName);
        setBillingSSN(billingSSN);
        setCompanyTaxId(companyTaxId);
        setGender(gender);
        setBillingStreetName(billingStreetName);
        setBillingHouseNumber(billingHouseNumber);
        setBillingHouseExtension(billingHouseExtension);
        setShippingStreetName(shippingStreetName);
        setShippingHouseNumber(shippingHouseNumber);
        setShippingHouseExtension(shippingHouseExtension);
        setShippingCareOf(shippingCareOf);
        setLineItems(lineItems);
        setAuthorizationType(authorizationType);
        setAuthenticationRedirect(authenticationRedirect);
        setAutoCapture(autoCapture);
        setVATamount(vatAmount);
        setTimestamp(timestamp);
    }

    public void setShippingHouseNumber(String shippingHouseNumber) {
        this.shippingHouseNumber = shippingHouseNumber;
    }

    public void setShippingHouseExtension(String shippingHouseExtension) {
        super.shippingHouseExtension = shippingHouseExtension;
    }

    public void setShippingCareOf(String shippingCareOf) {
        super.shippingCareOf = shippingCareOf;
    }

    public void setGender(String gender) {
        super.gender = gender;
    }

    public void setBillingStreetName(String streetName) {
        super.billingStreetName = streetName;
    }

    public void setShippingStreetName(String streetNameShippingInfo) {
        super.shippingStreetName = streetNameShippingInfo;
    }

    public void setBillingHouseNumber(String houseNumber) {
        super.billingHouseNumber = houseNumber;
    }

    public void setBillingHouseExtension(String houseExtension) {
        super.billingHouseExtension = houseExtension;
    }

    public void setAdditionalParameters(Map<String, String> additionalParameters) {
        super.additionalParameters = additionalParameters;
    }

    public void setAmount(BigDecimal amount) {
        super.amount = amount.doubleValue();
    }

    public void setBillingAddressLine3(String billingAddressLine3) {
        this.billingAddressLine3 = billingAddressLine3;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        super.billingAddressLine1 = billingAddressLine1;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        super.billingAddressLine2 = billingAddressLine2;
    }

    public void setBillingBuyerVatNumber(String billingBuyerVATNumber) {
        this.billingBuyerVATNumber = billingBuyerVATNumber;
    }

    public void setBillingBuyerType(String billingBuyerType) {
        this.billingBuyerType = billingBuyerType;
    }

    public void setBillingCity(String billingCity) {
        super.billingCity = billingCity;
    }

    public void setBillingCompanyName(String billingCompanyName) {
        this.billingCompanyName = billingCompanyName;
    }

    public void setBillingCountryCode(String billingCountryCode) {
        super.billingCountryCode = billingCountryCode;
    }

    public void setBillingEmailAddress(String billingEmailAddress) {
        super.billingEmailAddress = billingEmailAddress;
    }

    public void setBillingFirstName(String billingFirstName) {
        super.billingFirstName = billingFirstName;
    }

    public void setBillingFullName(String billingFullName) {
        super.billingFullName = billingFullName;
    }

    public void setBillingLastName(String billingLastName) {
        super.billingLastName = billingLastName;
    }

    public void setBillingMobilePhone(String billingMobilePhone) {
        super.billingMobilePhone = billingMobilePhone;
    }

    public void setBillingPhone(String billingPhone) {
        super.billingPhone = billingPhone;
    }

    public void setBillingSSN(String billingSSN) {
        super.billingSSN = billingSSN;
    }

    public void setCompanyTaxId(String companyTaxId) {this.companyTaxId = companyTaxId; }

    public void setBillingStateProvince(String billingStateProvince) {
        super.billingStateProvince = billingStateProvince;
    }

    public void setBillingZipCode(String billingZipCode) {
        super.billingZipCode = billingZipCode;
    }

    /**
     * @param consumerCountry - ISO-3166, e.g. US. Used together with language to form the
     *                        locale being used by Payment Page
     */
    public void setConsumerCountry(String consumerCountry) {
        super.consumerCountry = consumerCountry;
    }

    /**
     * @param consumerLanguage - Three alphabetic letter, ISO-4217 code
     */
    public void setConsumerLanguage(String consumerLanguage) {
        super.consumerLanguage = consumerLanguage;
    }

    public void setCurrency(String currency) {
        super.currency = currency;
    }

    public void setDueDate(String dueDate) {
        super.dueDate = dueDate;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void setMid(Long mid) {
        super.mid = mid;
    }

    public void setOrderDescription(String orderDescription) {
        super.orderDescription = orderDescription;
    }

    public void setOrderDetailDescription(String orderDetailDescription) {
        super.orderDetailDescription = orderDetailDescription;
    }

    public void setOrderId(String orderId) {
        super.orderId = orderId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        super.paymentMethodId = paymentMethodId;
    }

    public void setPaymentPlanCode(String paymentPlanCode) {
        this.paymentPlanCode = paymentPlanCode;
    }

    public void setPosId(String posId) {
        super.posId = posId;
    }

    public void setPosDesc(String posDesc) {
        super.posDesc = posDesc;
    }

    /**
     * @param returnUrl - When consumer has completed the payment page session this
     *                  URL will be used for redirecting consumer back to merchant.
     */
    public void setReturnUrl(String returnUrl) {
        super.returnUrl = returnUrl;
    }

    /**
     * @param timeLimit - Maximum time in seconds for the PaymentPageRequest to be
     *                  valid. Set this to zero for an unlimited timeout.
     */
    public void setTimeLimit(Integer timeLimit) {
        super.timeLimit = timeLimit;
    }

    public void setShippingAddressLine1(String shippingAddressLine1) {
        super.shippingAddressLine1 = shippingAddressLine1;
    }

    public void setShippingAddressLine2(String shippingAddressLine2) {
        super.shippingAddressLine2 = shippingAddressLine2;
    }

    public void setShippingAddressLine3(String shippingAddressLine3) {
        this.shippingAddressLine3 = shippingAddressLine3;
    }

    public void setShippingCity(String shippingCity) {
        super.shippingCity = shippingCity;
    }

    public void setShippingCompanyName(String shippingCompanyName) {
        this.shippingCompanyName = shippingCompanyName;
    }

    public void setShippingCountryCode(String shippingCountryCode) {
        super.shippingCountryCode = shippingCountryCode;
    }

    public void setShippingEmailAddress(String shippingEmailAddress) {
        super.shippingEmailAddress = shippingEmailAddress;
    }

    public void setShippingFirstName(String shippingFirstName) {
        super.shippingFirstName = shippingFirstName;
    }

    public void setShippingFullName(String shippingFullName) {
        super.shippingFullName = shippingFullName;
    }

    public void setShippingLastName(String shippingLastName) {
        super.shippingLastName = shippingLastName;
    }

    public void setShippingMobilePhone(String shippingMobilePhone) {
        super.shippingMobilePhone = shippingMobilePhone;
    }

    public void setShippingPhone(String shippingPhone) {
        super.shippingPhone = shippingPhone;
    }

    public void setShippingStateProvince(String shippingStateProvince) {
        super.shippingStateProvince = shippingStateProvince;
    }

    public void setShippingZipCode(String shippingZipCode) {
        super.shippingZipCode = shippingZipCode;
    }

    /**
     * @param storeFlag - Indicates that a token should be stored. '0' = Store not
     *                  used, '1' = Store and Debit/Authorize, '2' = Store only
     */
    public void setStoreFlag(StoreFlag storeFlag) {
        super.storeFlag = storeFlag.getVal();
    }

    public void setSubMerchantId(String subMerchantId) {
        super.subMerchantId = subMerchantId;
    }

    public void setToken(String token) {
        super.token = token;
    }

    public void setTransactionChannel(String transactionChannel) {
        super.transactionChannel = transactionChannel;
    }

    public void setTransactionType(String transactionType) {
        super.transactionType = transactionType;
    }

    public void setVATamount(BigDecimal vatAmount) {
        super.vatAmount = vatAmount.doubleValue();
    }

    public void setVATRate(Double vatRate) {
        super.vatRate = vatRate;
    }

    public void setRecurringType(String recurringType) {
        super.recurringType = recurringType;
    }

    public void setBirthDate(String birthdate) {
        super.birthDate = birthdate;
    }

    public void setCompanyResponsibleBirthDate(String birthdate) {
        super.companyResponsibleBirthDate = birthdate;
    }

    public void setCompanyResponsibleFullName(String fullName) {
        super.companyResponsibleFullName = fullName;
    }

    public void setCompanyResponsibleVatNumber(String vatNumber) {
        super.companyResponsibleVATNumber = vatNumber;
    }

    public void setAuthorizationType(AuthorizationType authorizationType) {
        if (authorizationType != null) {
            super.authorizationType = authorizationType.value();
        }
    }

    public void setAuthenticationRedirect(AuthenticationRedirect authenticationRedirect) {
        if (authenticationRedirect != null) {
            this.authenticationRedirect = authenticationRedirect.value();
        }
    }

    public void setAutoCapture(boolean autoCapture) {
        this.autoCapture = autoCapture;
        if (autoCapture) {
            setTransactionType("DEBIT");
        } else {
            setTransactionType("AUTHORIZE");
        }
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public enum StoreFlag {
        NO_STORE(0), STORE(1), STORE_ONLY(2);
        private final int val;

        StoreFlag(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}