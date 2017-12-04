package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.Transaction;
import com.digitalriver.worldpayments.api.utils.Parameter;

import java.util.Date;

public class AbstractPaymentResponse {
    @Parameter(shortName = "A")
    public Long mid;
    @Parameter(shortName = "B")
    public String status;
    @Parameter(shortName = "D")
    public String paymentMethod;
    @Parameter(shortName = "E")
    public String orderId;
    @Parameter(shortName = "F")
    public Date timestamp;
    @Parameter(shortName = "G")
    public String VResId;
    @Parameter(shortName = "H")
    public String PAResId;
    @Parameter(shortName = "I")
    public String dddsStatus;
    @Parameter(shortName = "J")
    public String POSId;
    @Parameter(shortName = "K")
    public String CardTxType;
    @Parameter(shortName = "L")
    public String CardTxId;
    @Parameter(shortName= "M")
    public String cardType;
    @Parameter(shortName= "P")
    public String expirationDate;
    @Parameter(shortName = "Q")
    public String StoreCardType;
    @Parameter(shortName = "R")
    public String IbpTxId;
    @Parameter(shortName = "S")
    public String IbpTxType;
    @Parameter(shortName = "T")
    public Boolean redirected;
    @Parameter(shortName= "U")
    public String maskedCardNumber;
    @Parameter(shortName = "X")
    public String eftReferenceId;
    @Parameter(shortName = "Y")
    public String eftPaymentSlipUrl;
    @Parameter(shortName = "Z")
    public Long eftTxId;
    @Parameter(shortName = "AA")
    public Long directDebitTxId;
    @Parameter(shortName = "AB")
    public Long payoutTxId;
    @Parameter(shortName= "AC")
    public Long avsAnswerCode;
    @Parameter(shortName= "AD")
    public String avsResponse;
    @Parameter(shortName= "AE")
    public String acquirerAnswerCode;
    @Parameter(shortName= "AF")
    public Long clientAnswerCode;
    @Parameter(shortName= "AG")
    public Long cvAnswerCode;
    @Parameter(shortName= "AH")
    public String cvResponse;
    @Parameter(shortName= "AI")
    public String paymentMethodName;
    @Parameter(shortName= "AJ")
    public String acquirerAuthCode;
    @Parameter(shortName= "AK")
    public String houseExtension;
    @Parameter(shortName= "AL")
    public String houseNumber;
    @Parameter(shortName= "AM")
    public String streetName;
    @Parameter(shortName= "AN")
    public String gender;
    @Parameter(shortName= "AO")
    public String birthDate;
    @Parameter(shortName= "AU")
    public String answerDescription;
    @Parameter(shortName= "AV")
    public Long PaymentPlanCode;
    @Parameter(shortName= "AW")
    public String socialSecNumber;
    @Parameter(shortName= "AX")
    public String firstName;
    @Parameter(shortName= "AY")
    public String lastName;
    @Parameter(shortName= "AZ")
    public String city;
    @Parameter(shortName= "AAA")
    public String countryCode;
    @Parameter(shortName= "AAB")
    public String zipCode;
    @Parameter(shortName= "AAC")
    public String addressLine1;
    public TokenizationResult tokenizationResult;
    public Transaction transaction;
}
