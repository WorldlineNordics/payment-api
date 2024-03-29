package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

import java.util.List;
import java.util.Map;

/**
 * When reading this code:
 *
 * The 'Parameter' annotation describes: * what a param is 'called' in the url when you set merchant id the
 * 'createRedirectUrl' will translate it to: A=123456789 * if it's required or not (IllegalArgumentException if you fail
 * to set value for a required param) * regEx is a simple(?) reg-ex that the provided value is matched against
 * ([0-9]{8,11} means 8-11 digits) * max length, self explanatory note that no param has both max length and reg-ex set.
 */
public abstract class AbstractPaymentPageRequest {

	@Parameter(shortName = "A", required = true, regEx = "[0-9]{6,11}")
	protected Long mid;

	@Parameter(shortName = "B")
	protected String subMerchantId;

	@Parameter(shortName = "C", maxLength = 50)
	protected String posId;

	@Parameter(shortName = "D", required = true, regEx = "(Web Online|Mail|Telephone|Fax|FaceToFace|Cash Register)")
	protected String transactionChannel;

	@Parameter(shortName = "E", regEx = "(?i)debit|authorize|refund")
	protected String transactionType;

	@Parameter(shortName = "F")
	protected String token;

	@Parameter(shortName = "G", maxLength = 50)
	protected String orderId;

	@Parameter(shortName = "H")
	protected String orderDescription;

	@Parameter(shortName = "I")
	protected String orderDetailDescription;

	@Parameter(shortName = "J")
	protected Double amount;

	@Parameter(shortName = "K", required = true, regEx = "[A-Za-z]{3}")
	protected String currency;

	@Parameter(shortName = "L")
	protected Double vatAmount;

	@Parameter(shortName = "M")
	protected Double vatRate;

	@Parameter(shortName = "T", required = true, regEx = "[A-Z]{2}")
	protected String consumerCountry;

	@Parameter(shortName = "U", required = true, regEx = "[a-z]{2}")
	protected String consumerLanguage;

	@Parameter(shortName = "V")
	protected String returnUrl;

	@Parameter(shortName = "W")
	protected Integer timeLimit;

	@Parameter(shortName = "Y")
	protected Map<String, String> additionalParameters;

	@Parameter(shortName = "Z")
	protected Integer paymentMethodId;

	@Parameter(shortName = "AA", regEx = "[0-2]{1}")
	protected Integer storeFlag;

	@Parameter(shortName = "AB")
	String templateReference;

	@Parameter(shortName = "AG")
	protected String billingAddressLine1;

	@Parameter(shortName = "AH")
	protected String billingAddressLine2;

	@Parameter(shortName = "AI")
	protected String billingCity;

	@Parameter(shortName = "AJ")
	protected String billingStateProvince;

	@Parameter(shortName = "AK")
	protected String billingZipCode;

	@Parameter(shortName = "AL", regEx = "[A-Za-z]{2}")
	protected String billingCountryCode;

	@Parameter(shortName = "AM")
	protected String billingEmailAddress;

	@Parameter(shortName = "AN")
	protected String billingPhone;

	@Parameter(shortName = "AO")
	protected String billingMobilePhone;

	@Parameter(shortName = "AP")
	protected String billingLastName;

	@Parameter(shortName = "AQ")
	protected String billingFirstName;

	@Parameter(shortName = "AR")
	protected String billingFullName;

	@Parameter(shortName = "AS")
	protected String shippingAddressLine1;

	@Parameter(shortName = "AT")
	protected String shippingAddressLine2;

	@Parameter(shortName = "AU")
	protected String shippingCity;

	@Parameter(shortName = "AV")
	protected String shippingStateProvince;

	@Parameter(shortName = "AW")
	protected String shippingZipCode;

	@Parameter(shortName = "AX", regEx = "[A-Za-z]{2}")
	protected String shippingCountryCode;

	@Parameter(shortName = "AY")
	protected String shippingEmailAddress;

	@Parameter(shortName = "AZ")
	protected String shippingPhone;

	// FIXME format?
	@Parameter(shortName = "BA")
	protected String dueDate;

	@Parameter(shortName = "BB")
	protected String paymentPlanCode;

	@Parameter(shortName = "BC")
	protected String billingCompanyName;

	@Parameter(shortName = "BD", maxLength = 25)
	protected String billingBuyerVATNumber;

	@Parameter(shortName = "BE", regEx = "(?i)individual|business")
	protected String billingBuyerType;

	@Parameter(shortName = "BG")
	protected String shippingCompanyName;

	@Parameter(shortName = "BH")
	protected String shippingAddressLine3;

	@Parameter(shortName = "BJ")
	protected String billingAddressLine3;

	@Parameter(shortName = "BT", regEx = "^((19\\d\\d)|([2-9]\\d\\d\\d))(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])")
	protected String birthDate;

	@Parameter(shortName = "CB", regEx = "^((19\\d\\d)|([2-9]\\d\\d\\d))(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])")
	protected String companyResponsibleBirthDate;

	@Parameter(shortName = "CN", maxLength = 50)
	protected String companyResponsibleFullName;

	@Parameter(shortName = "CV", maxLength = 25)
	protected String companyResponsibleVATNumber;

	@Parameter(shortName = "EA", regEx = "(?i)NOT_RECURRING|FIRST_RECURRING|SUBSEQUENT_RECURRING|LAST_RECURRING")
	protected String recurringType;

	@Parameter(shortName = "PD", maxLength = 50)
	protected String posDesc;

	@Parameter(shortName = "AAA")
	protected String shippingMobilePhone;

	@Parameter(shortName = "AAB")
	protected String shippingLastName;

	@Parameter(shortName = "AAC")
	protected String shippingFirstName;

	@Parameter(shortName = "AAD")
	protected String shippingFullName;

	@Parameter(shortName = "AAE", maxLength = 30)
	protected String billingSSN;

	@Parameter(shortName = "AAF", maxLength = 50)
	protected String companyTaxId;

	@Parameter(shortName = "AAG")
	protected String gender;

	@Parameter(shortName = "AAH")
	protected String billingStreetName;

	@Parameter(shortName = "AAI")
	protected String billingHouseNumber;

	@Parameter(shortName = "AAJ")
	protected String billingHouseExtension;

	@Parameter(shortName = "AAK")
	protected String shippingStreetName;

	@Parameter(shortName = "AAL")
	protected String shippingHouseNumber;

	@Parameter(shortName = "AAM")
	protected String shippingHouseExtension;

	@Parameter(shortName = "AAN")
	protected String shippingCareOf;

	protected List<LineItem> lineItems;

	@Parameter(shortName = "AAO", required = false)
	protected String authorizationType;

	@Parameter(shortName = "AAP", regEx = "(?i)NOREDIRECT|REDIRECT|REDIRECTONLY")
	protected String authenticationRedirect;

	@Parameter(shortName = "AAQ", required = true)
	protected boolean autoCapture;

	@Parameter(shortName = "AAR")
	protected Long timestamp;

	@Parameter(shortName = "AAT", regEx = "(?i)CIT_FIRST_TIME|CIT_USE_STORED|MIT_USE_STORED")
	protected String storedCredentialIndicator;

	@Parameter(shortName = "AAU", regEx = "(?i)UNSCHEDULED|RECURRING|RESUBMISSION|DELAYED_CHARGES|NO_SHOW|UNDEFINED")
	protected String storedCredentialReason;

	@Parameter(shortName = "AAV")
	protected String schemeReferenceId;
	
	@Parameter(shortName="ABA")
	protected String purchaseInstallment;
    
    @Parameter(shortName="ABB")
    protected String md;
    
    @Parameter(shortName="ABC")
    protected String acctID;
    
    @Parameter(shortName="ABD")
    protected String acctType;

	@Parameter(shortName="ABE")
	protected String addrMatch;
    
    @Parameter(shortName="ABF")
    protected String messageCategory;
    
    @Parameter(shortName="ABG")
    protected String purchaseDate;
    
    @Parameter(shortName="ABH")
    protected String transType;
    
    @Parameter(shortName="ABI")
    protected String threeRIInd;
    
    @Parameter(shortName="ABJ")
    protected String threeDSRequestorAuthenticationInd;
    
    @Parameter(shortName="ABK")
    protected String threeDSRequestorChallengeInd;
    
    @Parameter(shortName="ABL")
    protected String challengeWindowSize;
    
    @Parameter(shortName="ABM")
    protected String shipIndicator;
    
    @Parameter(shortName="ABN")
    protected String deliveryTimeframe;
    
    @Parameter(shortName="ABO")
    protected String deliveryEmailAddress;
    
    @Parameter(shortName="ABP")
    protected String reorderItemsInd;
    
    @Parameter(shortName="ABQ")
    protected String preOrderPurchaseInd;
    
    @Parameter(shortName="ABR")
    protected String preOrderDate;
    
    @Parameter(shortName="ABS")
    protected String giftCardAmount;
    
    @Parameter(shortName="ABT")
    protected String giftCardCurr;
    
    @Parameter(shortName="ABU")
    protected String giftCardCount;
    
    @Parameter(shortName="ABV")
    protected String chAccAgeInd;
    
    @Parameter(shortName="ABW")
    protected String chAccDate;
    
    @Parameter(shortName="ABX")
    protected String chAccChangeInd;
    
    @Parameter(shortName="ABY")
    protected String chAccChange;
    
    @Parameter(shortName="ABZ")
    protected String chAccPwChangeInd;
    
    @Parameter(shortName="ACA")
    protected String chAccPwChange;
    
    @Parameter(shortName="ACB")
    protected String nbPurchaseAccount;
    
    @Parameter(shortName="ACC")
    protected String provisionAttemptsDay;
    
    @Parameter(shortName="ACD")
    protected String txnActivityDay;
    
    @Parameter(shortName="ACE")
    protected String txnActivityYear;
    
    @Parameter(shortName="ACF")
    protected String shipAddressUsageInd;
    
    @Parameter(shortName="ACG")
    protected String shipAddressUsage;
    
    @Parameter(shortName="ACH")
    protected String shipNameIndicator;
    
    @Parameter(shortName="ACI")
    protected String paymentAccInd;
    
    @Parameter(shortName="ACJ")
    protected String paymentAccAge;
    
    @Parameter(shortName="ACK")
    protected String suspiciousAccActivity;
    
    @Parameter(shortName="ACL")
    protected String threeDSReqAuthMethod;
    
    @Parameter(shortName="ACM")
    protected String threeDSReqAuthTimestamp;
    
    @Parameter(shortName="ACN")
    protected String threeDSReqPriorRef;
    
    @Parameter(shortName="ACO")
    protected String threeDSReqPriorAuthMethod;
    
    @Parameter(shortName="ACP")
    protected String threeDSReqPriorAuthTimestamp;
    
    @Parameter(shortName="ACX")
    protected String libraryVersion;
    
    @Parameter(shortName="ACY")
    protected String sca;
    
    @Parameter(shortName = "ADA", regEx = "(Proceed when SCA Successful|Proceed when SCA Attempted|Proceed without SCA)")
	protected String paymentAuthenticationLevel;
    
    @Parameter(shortName = "ADC")
	protected boolean isShorterResponse;
}
