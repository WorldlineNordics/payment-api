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

	@Parameter(shortName = "A", required = true, regEx = "[0-9]{8,11}")
	public Long mid;

	@Parameter(shortName = "B")
	public String subMerchantId;

	@Parameter(shortName = "C", maxLength = 50)
	public String posId;

	@Parameter(shortName = "D", required = true, regEx = "(Web Online|Mail|Telephone|Fax|FaceToFace|Cash register)")
	public String transactionChannel;

	@Parameter(shortName = "E", regEx = "(?i)debit|authorize|refund")
	public String transactionType;

	@Parameter(shortName = "F")
	public String token;

	@Parameter(shortName = "G", required = true, maxLength = 50)
	public String orderId;

	@Parameter(shortName = "H")
	public String orderDescription;

	@Parameter(shortName = "I")
	public String orderDetailDescription;

	@Parameter(shortName = "J")
	public Double amount;

	@Parameter(shortName = "K", required = true, regEx = "[A-Za-z]{3}")
	public String currency;

	@Parameter(shortName = "L")
	public Double vatAmount;

	@Parameter(shortName = "M")
	public Double vatRate;

	@Parameter(shortName = "T", required = true, regEx = "[A-Z]{2}")
	public String consumerCountry;

	@Parameter(shortName = "U", required = true, regEx = "[a-z]{2}")
	public String consumerLanguage;

	@Parameter(shortName = "V")
	public String returnUrl;

	@Parameter(shortName = "W")
	public Integer timeLimit;

	@Parameter(shortName = "Y")
	public Map<String, String> additionalParameters;

	@Parameter(shortName = "Z")
	public Integer paymentMethodId;

	@Parameter(shortName = "AA", regEx = "[0-2]{1}")
	public Integer storeFlag;

	@Parameter(shortName = "AB")
	String templateReference;

	@Parameter(shortName = "AG")
	public String billingAddressLine1;

	@Parameter(shortName = "AH")
	public String billingAddressLine2;

	@Parameter(shortName = "AI")
	public String billingCity;

	@Parameter(shortName = "AJ")
	public String billingStateProvince;

	@Parameter(shortName = "AK")
	public String billingZipCode;

	@Parameter(shortName = "AL", regEx = "[A-Za-z]{2}")
	public String billingCountryCode;

	@Parameter(shortName = "AM")
	public String billingEmailAddress;

	@Parameter(shortName = "AN")
	public String billingPhone;

	@Parameter(shortName = "AO")
	public String billingMobilePhone;

	@Parameter(shortName = "AP")
	public String billingLastName;

	@Parameter(shortName = "AQ")
	public String billingFirstName;

	@Parameter(shortName = "AR")
	public String billingFullName;

	@Parameter(shortName = "AS")
	public String shippingAddressLine1;

	@Parameter(shortName = "AT")
	public String shippingAddressLine2;

	@Parameter(shortName = "AU")
	public String shippingCity;

	@Parameter(shortName = "AV")
	public String shippingStateProvince;

	@Parameter(shortName = "AW")
	public String shippingZipCode;

	@Parameter(shortName = "AX", regEx = "[A-Za-z]{2}")
	public String shippingCountryCode;

	@Parameter(shortName = "AY")
	public String shippingEmailAddress;

	@Parameter(shortName = "AZ")
	public String shippingPhone;

	// FIXME format?
	@Parameter(shortName = "BA")
	public String dueDate;

	@Parameter(shortName = "BB")
	public String paymentPlanCode;

	@Parameter(shortName = "BC")
	public String billingCompanyName;

	@Parameter(shortName = "BD", maxLength = 25)
	public String billingBuyerVATNumber;

	@Parameter(shortName = "BE", regEx = "(?i)individual|business")
	public String billingBuyerType;

	@Parameter(shortName = "BG")
	public String shippingCompanyName;

	@Parameter(shortName = "BH")
	public String shippingAddressLine3;

	@Parameter(shortName = "BJ")
	public String billingAddressLine3;

	@Parameter(shortName = "BT", regEx = "^((19\\d\\d)|([2-9]\\d\\d\\d))(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])")
	public String birthDate;

	@Parameter(shortName = "CB", regEx = "^((19\\d\\d)|([2-9]\\d\\d\\d))(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])")
	public String companyResponsibleBirthDate;

	@Parameter(shortName = "CN", maxLength = 50)
	public String companyResponsibleFullName;

	@Parameter(shortName = "CV", maxLength = 25)
	public String companyResponsibleVATNumber;

	@Parameter(shortName = "EA", regEx = "(?i)NOT_RECURRING|FIRST_RECURRING|SUBSEQUENT_RECURRING|LAST_RECURRING")
	public String recurringType;

	@Parameter(shortName = "PD", maxLength = 50)
	public String posDesc;

	@Parameter(shortName = "AAA")
	public String shippingMobilePhone;

	@Parameter(shortName = "AAB")
	public String shippingLastName;

	@Parameter(shortName = "AAC")
	public String shippingFirstName;

	@Parameter(shortName = "AAD")
	public String shippingFullName;

	@Parameter(shortName = "AAE", maxLength = 30)
	public String billingSSN;

	@Parameter(shortName = "AAF", maxLength = 50)
	public String companyTaxId;

	@Parameter(shortName = "AAG")
	public String gender;

	@Parameter(shortName = "AAH")
	public String billingStreetName;

	@Parameter(shortName = "AAI")
	public String billingHouseNumber;

	@Parameter(shortName = "AAJ")
	public String billingHouseExtension;

	@Parameter(shortName = "AAK")
	public String shippingStreetName;

	@Parameter(shortName = "AAL")
	public String shippingHouseNumber;

	@Parameter(shortName = "AAM")
	public String shippingHouseExtension;

	@Parameter(shortName = "AAN")
	public String shippingCareOf;

	public List<LineItem> lineItems;

	@Parameter(shortName = "AAO", required = false)
	public String authorizationType;

	@Parameter(shortName = "AAP", regEx = "(?i)NOREDIRECT|REDIRECT|REDIRECTONLY")
	public String authenticationRedirect;

	@Parameter(shortName = "AAQ", required = true)
	public boolean autoCapture;

}
