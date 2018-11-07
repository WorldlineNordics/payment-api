package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

import java.math.BigDecimal;
import java.util.Date;

public class AbstractPaymentResponse {
    @Parameter(shortName = "A")
    protected Long mid;
    @Parameter(shortName = "B")
    protected String status;
    @Parameter(shortName = "D")
    protected String paymentMethod;
    @Parameter(shortName = "E")
    protected String orderId;
    @Parameter(shortName = "F")
    protected Date timestamp;
    @Parameter(shortName = "G")
    protected String VResId;
    @Parameter(shortName = "H")
    protected String PAResId;
    @Parameter(shortName = "I")
    protected String dddsStatus;
    @Parameter(shortName = "J")
    protected String POSId;
    @Parameter(shortName = "K")
    protected String CardTxType;
    @Parameter(shortName = "L")
    protected String CardTxId;
    @Parameter(shortName = "M")
    String cardType;
    @Parameter(shortName = "P")
    protected String expirationDate;
    @Parameter(shortName = "Q")
    protected String StoreCardType;
    @Parameter(shortName = "R")
    protected String IbpTxId;
    @Parameter(shortName = "S")
    protected String IbpTxType;
    @Parameter(shortName = "T")
    Boolean redirected;
    @Parameter(shortName = "U")
    protected String maskedCardNumber;
    @Parameter(shortName = "X")
    protected String eftReferenceId;
    @Parameter(shortName = "Y")
    protected String eftPaymentSlipUrl;
    @Parameter(shortName = "Z")
    protected Long eftTxId;
    @Parameter(shortName = "AA")
    protected Long directDebitTxId;
    @Parameter(shortName = "AB")
    protected Long payoutTxId;
    @Parameter(shortName = "AC")
    protected Long avsAnswerCode;
    @Parameter(shortName = "AD")
    protected String avsResponse;
    @Parameter(shortName = "AE")
    protected String acquirerAnswerCode;
    @Parameter(shortName = "AF")
    protected Long clientAnswerCode;
    @Parameter(shortName = "AG")
    protected Long cvAnswerCode;
    @Parameter(shortName = "AH")
    protected String cvResponse;
    @Parameter(shortName = "AI")
    protected String paymentMethodName;
    @Parameter(shortName = "AJ")
    protected String acquirerAuthCode;
    @Parameter(shortName = "AK")
    protected String houseExtension;
    @Parameter(shortName = "AL")
    protected String houseNumber;
    @Parameter(shortName = "AM")
    protected String streetName;
    @Parameter(shortName = "AN")
    protected String gender;
    @Parameter(shortName = "AO")
    protected String birthDate;
    @Parameter(shortName = "AU")
    protected String answerDescription;
    @Parameter(shortName = "AV")
    protected Long PaymentPlanCode;
    @Parameter(shortName = "AW")
    protected String socialSecNumber;
    @Parameter(shortName = "AX")
    protected String firstName;
    @Parameter(shortName = "AY")
    protected String lastName;
    @Parameter(shortName = "AZ")
    protected String city;
    @Parameter(shortName = "AAA")
    protected String countryCode;
    @Parameter(shortName = "AAB")
    protected String zipCode;
    @Parameter(shortName = "AAC")
    protected String addressLine1;
    protected TokenizationResult tokenizationResult;
    protected Transaction transaction;
    @Parameter(shortName = "AAH")
    protected BigDecimal orderAmount;
    @Parameter(shortName = "AAI")
    public BigDecimal fulfilmentAmount;
    @Parameter(shortName = "AAJ")
    public BigDecimal capturedAmount;
    @Parameter(shortName = "AAK")
    public BigDecimal refundedAmount;
    @Parameter(shortName = "AAL")
    public String currency;
    @Parameter(shortName = "AAN")
    public boolean capturable;
    @Parameter(shortName = "AAO")
    public Integer paymentMethodId;
    public FraudResult fraudResult;
    @Parameter(shortName = "AAS")
    public String subMerchantId;
    @Parameter(shortName = "AAT")
    public Long refTransactionId;
    @Parameter(shortName = "AAU")
    public String acquirerReferenceId;
    @Parameter(shortName = "AAW")
    public String posDescription;
    
    @Parameter(shortName= "AAX")
    public String redirectUrl;

    @Parameter(shortName= "AAY")
    protected String authenticationStatus;
    
    @Parameter(shortName = "AAZ")
    protected String redirectParameters;
    
    @Parameter(shortName = "ABA")
    protected String redirectMethod;

    @Parameter(shortName = "ABB")
    protected String acsToken;

}
