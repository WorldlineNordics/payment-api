package com.merchantcompany;

import com.digitalriver.worldpayments.api.AuthorizationType;
import com.digitalriver.worldpayments.api.PaymentPageHandler;
import com.digitalriver.worldpayments.api.PaymentPageRequest;
import com.digitalriver.worldpayments.api.PaymentPageResponse;
import com.digitalriver.worldpayments.api.StoredCredentialIndicator;
import com.digitalriver.worldpayments.api.StoredCredentialReason;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;

public class Example {

    private static PaymentPageRequest buildRequest() {
        PaymentPageRequest request = new PaymentPageRequest();
        request.setMid(1234567890L);
        request.setSubMerchantId("12");
        request.setPosId("ABC");
        request.setTransactionChannel("Web Online");
        request.setOrderId("orderid");
        request.setAmount(123.0);
        request.setCurrency("SEK");
        request.setConsumerCountry("SE");
        request.setConsumerLanguage("sv");
        request.setReturnUrl("http://merchant.com?f=3&f=q");
        request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);
        request.setStoredCredentialIndicator(StoredCredentialIndicator.CIT_USE_STORED);
        request.setStoredCredentialReason(StoredCredentialReason.UNSCHEDULED);
        request.setSchemeReferenceId("SchemeReferenceId");
        return request;
    }

    public static void main(String[] args) {

        PaymentPageHandler paymentPageHandler = new PaymentPageHandler(PaymentPageHandler.DEFAULT_PRODUCTION_BASE_URL, new JKSKeyHandlerV6("src/test/resources/merchant.jks", "merchant", "merchant", "drwp_cert"));

        PaymentPageRequest request = buildRequest();

        String redirectUrl = paymentPageHandler.createRedirectUrl(request);

        System.out.println(redirectUrl);

        // sendRedirect(redirectUrl);
        // get back a response when consumer comes back

        String respString = "BgAAA-sAAAPqLmG9awk-ZuE00dxJc0fjlsCQsSTSSfpRdCtrGIMXnhIBHGm-gnhdFG0vKX_t1CttI1Ss2ySGScKgDJPYhxO6wcjL22u932xZAdN72fo5vFq-3pdm5SSehDCLv0IWj5qfwBzPCB2QCE_kW-N-HbrRpjYN6c3h-Kv_h0kVRESPQd7pSaL86XRfl02f-peCThc4KpHMPRBUCVVjPQQfOJx6YqrNqmBjcTorrCzNlDXRruGIdlgrPxyKr2TmyhKs-v-05G_4B4ZEdkSrmun1ulDoVFYlzcTuXGr4o-mwHG8ZrLpLWYL47ODUTxgyOghJe6lw7IsB4fI9kU2ka7-unAj5CmIoI5lW7TjNNqkU_jUIDkq40WxuMHJujWuyO1qIng5jvKLIcB8B7kTFMpDmjbwpp0371Qfz6zly8OzTZprKhydoVZu9myeqaOBgb9agvhTfRdzo0LHrsL0hKJcSIRD4y_xFhWqY4KNMJ_wHi4huwr1YvNqg2eNBeu1sb8g4ZsZ8Td0mvPEeSaLkSaQzZoviQP9knV9vVeWEqrojHpzN9vJ1ckyFdhHcs9iaodxDHfbeRdusmoCQ2XtiRnmXxW-TqN1-97vundXHEPiEUwMI8eU06bluOb-MGpuE2hTQhJtLMoWqWfS_GSK6sQb6kT_bUf7J9vHzzo1aXS4HNPDVkb_7WjYBrqLk6tvWdYFnp9C4DSZ42XU_HmlsAjpf_1lyDplxh9uIXFAl2PtSxWvLIrMSz6cSSDOKld2mbwgiXhT8exbklticT2OiFo_U5bF6-2XqYp0bvpVFh_cms-0D76_9X5hovg0vO1T0H1WKqfrQSprSWZ76S6CS1y0E2E9O815GC7SPAFD0SIRt2kmV0xIh254S7uX9oSrzGvwOwFjOtx8LU3zjpL6g1mLYUGxWiwPhtPl2_SNGBCcK0MhVh8Gl4zs5Ijj7HM7jbyjdxwxr-PkJdqfj14skZdXbBZ8H0uH07GycueXDngbjDyCI6XWigsIV_-n52kO1r8CDYi2wU2z2JPL2fqsPeBvoZQzAaQ==";
        PaymentPageResponse response = paymentPageHandler
                .unpackResponse(respString);

        System.out.println("Response: mid=" + response.getMid() + ", orderid="
                + response.getOrderId() + ", status="
                + response.getStatus());

    }
}
