package com.worldline.payments.api;

public class InitAuthenticationResponse extends AbstractPaymentResponse {

    public Long getMid() {
        return mid;
    }

    public String getOrderId() {
        return orderId;
    }

   
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

    public String getAcsToken() {
        return acsToken;
    }

}
