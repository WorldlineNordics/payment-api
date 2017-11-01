package com.digitalriver.worldpayments.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.digitalriver.worldpayments.api.utils.Parameter;

/**
 * When reading this code:
 *
 * The 'Parameter' annotation describes: * what a param is 'called' in the url when you set merchant id the
 * 'createRedirectUrl' will translate it to: A=123456789 * if it's required or not (IllegalArgumentException if you fail
 * to set value for a required param) * regEx is a simple(?) reg-ex that the provided value is matched against
 * ([0-9]{8,11} means 8-11 digits) * max length, self explanatory note that no param has both max length and reg-ex set.
 */
abstract class AbstractPaymentPageRequest {

    @Parameter(shortName = "A", required = true, regEx = "[0-9]{8,11}")
    Long mid;

    @Parameter(shortName = "B")
    String subMerchantId;

    @Parameter(shortName = "C", maxLength = 50)
    String posId;

    @Parameter(shortName = "D", required = true, regEx = "(Web Online|Mail|Telephone|Fax|FaceToFace|Cash register)")
    String transactionChannel;

    @Parameter(shortName = "E", regEx = "(?i)debit|authorize|refund")
    String transactionType;

    @Parameter(shortName = "F")
    String token;

    @Parameter(shortName = "G", required = true, maxLength = 50)
    String orderId;

    @Parameter(shortName = "H")
    String orderDescription;

    @Parameter(shortName = "I")
    String orderDetailDescription;

    @Deprecated
    @Parameter(shortName = "J")
    Double amountDouble;

    @Parameter(shortName = "K", required = true, regEx = "[A-Za-z]{3}")
    String currency;

    @Parameter(shortName = "L")
    Double vatAmount;

    @Parameter(shortName = "M")
    Double vatRate;

    @Parameter(shortName = "T", required = true, regEx = "[A-Z]{2}")
    String consumerCountry;

    @Parameter(shortName = "U", required = true, regEx = "[a-z]{2}")
    String consumerLanguage;

    @Parameter(shortName = "V")
    String returnUrl;

    @Parameter(shortName = "W")
    Integer timeLimit;

    @Parameter(shortName = "Y")
    Map<String, String> additionalParameters;

    @Parameter(shortName = "Z")
    Integer paymentMethodId;

    @Parameter(shortName = "AA", regEx = "[0-2]{1}")
    Integer storeFlag;

    @Parameter(shortName = "AB")
    String templateReference;

    @Parameter(shortName = "AG")
    String billingAddressLine1;

    @Parameter(shortName = "AH")
    String billingAddressLine2;

    @Parameter(shortName = "AI")
    String billingCity;

    @Parameter(shortName = "AJ")
    String billingStateProvince;

    @Parameter(shortName = "AK")
    String billingZipCode;

    @Parameter(shortName = "AL", regEx = "[A-Za-z]{2}")
    String billingCountryCode;

    @Parameter(shortName = "AM")
    String billingEmailAddress;

    @Parameter(shortName = "AN")
    String billingPhone;

    @Parameter(shortName = "AO")
    String billingMobilePhone;

    @Parameter(shortName = "AP")
    String billingLastName;

    @Parameter(shortName = "AQ")
    String billingFirstName;

    @Parameter(shortName = "AR")
    String billingFullName;

    @Parameter(shortName = "AS")
    String shippingAddressLine1;

    @Parameter(shortName = "AT")
    String shippingAddressLine2;

    @Parameter(shortName = "AU")
    String shippingCity;

    @Parameter(shortName = "AV")
    String shippingStateProvince;

    @Parameter(shortName = "AW")
    String shippingZipCode;

    @Parameter(shortName = "AX", regEx = "[A-Za-z]{2}")
    String shippingCountryCode;

    @Parameter(shortName = "AY")
    String shippingEmailAddress;

    @Parameter(shortName = "AZ")
    String shippingPhone;

    // FIXME format?
    @Parameter(shortName = "BA")
    String dueDate;

    @Parameter(shortName = "BB")
    String paymentPlanCode;

    @Parameter(shortName = "BC")
    String billingCompanyName;

    @Parameter(shortName = "BD", maxLength = 25)
    String billingBuyerVATNumber;

    @Parameter(shortName = "BE", regEx = "(?i)individual|business")
    String billingBuyerType;

    @Parameter(shortName = "BG")
    String shippingCompanyName;

    @Parameter(shortName = "BH")
    String shippingAddressLine3;

    @Parameter(shortName = "BJ")
    String billingAddressLine3;

    @Parameter(shortName = "BT", regEx = "^((19\\d\\d)|([2-9]\\d\\d\\d))(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])")
    String birthDate;

    @Parameter(shortName = "CB", regEx = "^((19\\d\\d)|([2-9]\\d\\d\\d))(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])")
    String companyResponsibleBirthDate;

    @Parameter(shortName = "CN", maxLength = 50)
    String companyResponsibleFullName;

    @Parameter(shortName = "CV", maxLength = 25)
    String companyResponsibleVATNumber;

    @Parameter(shortName = "EA", regEx = "(?i)NOT_RECURRING|FIRST_RECURRING|SUBSEQUENT_RECURRING|LAST_RECURRING")
    String recurringType;

    @Parameter(shortName = "PD", maxLength = 50)
    String posDesc;

    @Parameter(shortName = "AAA")
    String shippingMobilePhone;

    @Parameter(shortName = "AAB")
    String shippingLastName;

    @Parameter(shortName = "AAC")
    String shippingFirstName;

    @Parameter(shortName = "AAD")
    String shippingFullName;

    @Parameter(shortName = "AAE", maxLength = 30)
    String billingSSN;

    @Parameter(shortName = "AAF", maxLength = 50)
    String companyTaxId;

    @Parameter(shortName = "AAG")
    String gender;

    @Parameter(shortName = "AAH")
    String billingStreetName;

    @Parameter(shortName = "AAI")
    String billingHouseNumber;

    @Parameter(shortName = "AAJ")
    String billingHouseExtension;

    @Parameter(shortName = "AAK")
    String shippingStreetName;

    @Parameter(shortName = "AAL")
    String shippingHouseNumber;

    @Parameter(shortName = "AAM")
    String shippingHouseExtension;

    @Parameter(shortName = "AAN")
    String shippingCareOf;

    List<LineItem> lineItems;

    @Parameter(shortName = "AAO", required = false)
    String authorizationType;

    @Parameter(shortName = "AAP", regEx = "(?i)NOREDIRECT|REDIRECT|REDIRECTONLY")
    String authenticationRedirect;

    @Parameter(shortName = "AAQ")
    BigDecimal amount;
}
