package com.worldline.payments.api;

import com.digitalriver.worldpayments.api.NvpUtil;
import com.digitalriver.worldpayments.api.ParameterAnnotationHelper;
import com.digitalriver.worldpayments.api.security.SecurityHandler;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;
import com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl;
import com.digitalriver.worldpayments.api.utils.ParseUtil;

import java.util.Map;

/**
 * PaymentHandler is used for creating the encrypted string to be used when sending
 * @see PaymentRequest
 * @see PaymentResponse
 */
public class PaymentHandler {

    protected String deviceEndpoint;

    private static PaymentResponse createPaymentResponse(final Map<String, String> nvp) {

        PaymentResponse ppResponse = new PaymentResponse();
        try {
            ParameterAnnotationHelper.mapNvpToObject(ppResponse, nvp);
        } catch (Exception e) {
            throw new IllegalArgumentException("Contact Worldline support",
                    e);
        }

        return ppResponse;
    }
    
    private final SecurityHandler iSecurityHandler;

    /**
     * Create a PaymentPageHandler with a specified key handler The PaymentPageHandler is then used to create a redirect
     * URL used when redirecting consumer to PaymentPage.
     *
     * @param aKeyHandler
     *            a KeyHandler containing device endpoint keys
     */
    @Deprecated
    public PaymentHandler(JKSKeyHandlerV6 aKeyHandler) {
        this(new SecurityHandlerImpl(aKeyHandler));
    }

    @Deprecated
    public PaymentHandler(SecurityHandler aSecurityHandler) {
        iSecurityHandler = aSecurityHandler;
    }

    /**
     * Create a PaymentHandler with a specified key handler and endpoint for the Device Payment API.
     * @param keyHandler device endpoint keys
     * @param deviceEndpoint URL to Device Payment API
     */
    public PaymentHandler(JKSKeyHandlerV6 keyHandler, String deviceEndpoint) {
        iSecurityHandler = new SecurityHandlerImpl(keyHandler);
        this.deviceEndpoint = deviceEndpoint;
    }

    /**
     * Create a Device API Request to be used for Devices that processes with the Device API.
     *
     * @param request a request
     * @return JSON object to be passed to the Device API
     */
    public String createDeviceAPIRequest(PaymentRequest request) {
        Map<String, String> nvp = ParameterAnnotationHelper.mapObjectToNvp(request);
        String encryptedRequest = iSecurityHandler.encrypt(NvpUtil.createNvpString(nvp));

        return getResponseJson(encryptedRequest, deviceEndpoint);
    }

    protected String getResponseJson(String encryptedRequest, String deviceEndpoint) {
        if (hasQuotes(encryptedRequest) || hasQuotes(deviceEndpoint)) {
            throw new IllegalArgumentException("Cannot contain quotes.");
        }
        return "{ "
                + "\"version\": \"A\", "
                + "\"deviceEndpoint\": \""
                + deviceEndpoint
                + "\", \"encryptedPayload\": \""
                + encryptedRequest + "\""
                + "}";
    }

    protected boolean hasQuotes(String str) {
        return str.contains("\"");
    }

    /**
     * The received request is encrypted with the following method.
     *
     * @return the encrypted PaymentRequest object
     */
    @Deprecated
    public String encryptRequest(PaymentRequest request) {
    	Map<String, String> nvp = ParameterAnnotationHelper.mapObjectToNvp(request);
        return iSecurityHandler.encrypt(NvpUtil.createNvpString(nvp));
    }

	/**
     * When payment is done, it redirects the consumer back a response json string. The response string is encrypted and can be decrypted
     * with the following method.
     *
     * Be sure to have the response URLDecoded before calling this method
     *
     * @param encodedResponseString
     *            the encrypted response string received from PaymentPageServer
     * @return the decrypted PaymentResponse object
     */
    public PaymentResponse unpackResponse(String encodedResponseString) {

        String decodedResponse = iSecurityHandler
                .decrypt(encodedResponseString);

        Map<String, String> nvpMap = ParseUtil.parseWithEscape(decodedResponse,
                '=', ',');

        return createPaymentResponse(nvpMap);
    }
}
