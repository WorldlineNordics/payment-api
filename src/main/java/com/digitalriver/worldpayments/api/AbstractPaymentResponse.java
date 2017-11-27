package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

import java.util.Date;

public class AbstractPaymentResponse {
    @Parameter(shortName = "A")
    Long mid;
    @Parameter(shortName = "B")
    String status;
    @Parameter(shortName = "D")
    String paymentMethod;
    @Parameter(shortName = "E")
    String orderId;
    @Parameter(shortName = "F")
    Date timestamp;
    @Parameter(shortName = "G")
    String VResId;
    @Parameter(shortName = "H")
    String PAResId;
    @Parameter(shortName = "I")
    String dddsStatus;
    @Parameter(shortName = "J")
    String POSId;
    @Parameter(shortName = "K")
    String CardTxType;
    @Parameter(shortName = "L")
    String CardTxId;
    @Parameter(shortName= "M")
    String cardType;
    @Parameter(shortName= "P")
    String expirationDate;
    @Parameter(shortName = "Q")
    String StoreCardType;
    @Parameter(shortName = "R")
    String IbpTxId;
    @Parameter(shortName = "S")
    String IbpTxType;
    @Parameter(shortName = "T")
    Boolean redirected;
    @Parameter(shortName= "U")
    String maskedCardNumber;
    @Parameter(shortName = "X")
    String eftReferenceId;
    @Parameter(shortName = "Y")
    String eftPaymentSlipUrl;
    @Parameter(shortName = "Z")
    Long eftTxId;
    @Parameter(shortName = "AA")
    Long directDebitTxId;
    @Parameter(shortName = "AB")
    Long payoutTxId;
    @Parameter(shortName= "AC")
    Long avsAnswerCode;
    @Parameter(shortName= "AD")
    String avsResponse;
    @Parameter(shortName= "AE")
    String acquirerAnswerCode;
    @Parameter(shortName= "AF")
    Long clientAnswerCode;
    @Parameter(shortName= "AG")
    Long cvAnswerCode;
    @Parameter(shortName= "AH")
    String cvResponse;
    @Parameter(shortName= "AI")
    String paymentMethodName;
    @Parameter(shortName= "AJ")
    String acquirerAuthCode;
    @Parameter(shortName= "AK")
    String houseExtension;
    @Parameter(shortName= "AL")
    String houseNumber;
    @Parameter(shortName= "AM")
    String streetName;
    @Parameter(shortName= "AN")
    String gender;
    @Parameter(shortName= "AO")
    String birthDate;
    @Parameter(shortName= "AU")
    String answerDescription;
    @Parameter(shortName= "AV")
    Long PaymentPlanCode;
    @Parameter(shortName= "AW")
    String socialSecNumber;
    @Parameter(shortName= "AX")
    String firstName;
    @Parameter(shortName= "AY")
    String lastName;
    @Parameter(shortName= "AZ")
    String city;
    @Parameter(shortName= "AAA")
    String countryCode;
    @Parameter(shortName= "AAB")
    String zipCode;
    @Parameter(shortName= "AAC")
    String addressLine1;
    TokenizationResult tokenizationResult;
    Transaction transaction;
}
