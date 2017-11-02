package com.digitalriver.worldpayments.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Class used when creating a consumer redirect to PaymentPage
 *
 * @see PaymentPageHandler
 */
public class PaymentPageRequest extends AbstractPaymentPageRequest {

    public enum StoreFlag {
        NO_STORE(0), STORE(1), STORE_ONLY(2);
        private final int val;

        private StoreFlag(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public void setShippingHouseNumber(String shippingHouseNumber) {
        super.shippingHouseNumber = shippingHouseNumber;
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
    
    @Deprecated
    public void setAmount(Double amount) {
        super.amountDouble = amount;
    }

    public void setAmount(BigDecimal amount) {
        super.amount = amount;
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

    public void setBillingStateProvince(String billingStateProvince) {
        super.billingStateProvince = billingStateProvince;
    }

    public void setBillingZipCode(String billingZipCode) {
        super.billingZipCode = billingZipCode;
    }

    /**
     * @param consumerCountry - ISO-3166, e.g. US. Used together with language to form the locale being
     * used by Payment Page
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
     * @param returnUrl - When consumer has completed the payment page session this URL will be
     * used for redirecting consumer back to merchant.
     */
    public void setReturnUrl(String returnUrl) {
        super.returnUrl = returnUrl;
    }

    /**
     * @param timeLimit - Maximum time in seconds for the PaymentPageRequest to be valid. Set this
     * to zero for an unlimited timeout.
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
     * @param storeFlag - Indicates that a token should be stored. '0' = Store not used, '1' =
     * Store and Debit/Authorize, '2' = Store only
     */
    public void setStoreFlag(StoreFlag storeFlag) {
        super.storeFlag = storeFlag.getVal();
    }

    public void setSubMerchantId(String subMerchantId) {
        super.subMerchantId = subMerchantId;
    }

    /**
     * @param templateReference - Specifies which page to present in PaymentPage
     */
    public void setTemplateReference(String templateReference) {
        super.templateReference = templateReference;
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

    public void setVATamount(Double vatAmount) {
        super.vatAmount = vatAmount;
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
            super.authenticationRedirect = authenticationRedirect.value();
        }
    }
}