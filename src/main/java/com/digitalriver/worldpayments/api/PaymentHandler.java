package com.digitalriver.worldpayments.api;

import java.util.Map;
import com.digitalriver.worldpayments.api.security.SecurityHandler;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;
import com.digitalriver.worldpayments.api.security6.SecurityHandlerImpl;
import com.digitalriver.worldpayments.api.utils.ParseUtil;

/**
 * The responsibility of this class is to create a redirect URL (when redirecting the consumer to PaymentPage), and
 * unpack the response URL (that comes back when consumer has been redirected back to Merchant)
 * 
 * @see PaymentPageRequest
 * @see PaymentPageResponse
 */
public class PaymentHandler {
	
    static final PaymentPageResponse createPaymentPageResponse(
            final Map<String, String> nvp) {

        PaymentPageResponse ppResponse = new PaymentPageResponse();
        try {
            ParameterAnnotationHelper.mapNvpToObject(ppResponse, nvp);
        } catch (Exception e) {
            throw new IllegalArgumentException("Contact DRWP support",
                    e);
        }

        return ppResponse;
    }
    
    private final SecurityHandler iSecurityHandler;

    /**
     * Create a PaymentPageHandler with a specified key handler The PaymentPageHandler is then used to create a redirect
     * URL used when redirecting consumer to PaymentPage.
     *
     * @param aBaseUrl
     *            the base of the response url @see #DEFAULT_PRODUCTION_BASE_URL @see #DEFAULT_TEST_BASE_URL
     * @param aKeyHandler
     *            a KeyHandler containing payment page keys
     */
    
    public PaymentHandler(JKSKeyHandlerV6 aKeyHandler) {
        this(new SecurityHandlerImpl(aKeyHandler));
    }

    public PaymentHandler(SecurityHandler aSecurityHandler) {
        iSecurityHandler = aSecurityHandler;
    }
    
    /**
     * The received request is encrypted with the following method.
     *
     *
     * @param encodedResponseString
     *            the encrypted response json string received from PaymentPageServer
     * @return the decrypted PaymentResponse object
     */
    public String encryptRequest(PaymentRequest request) {
    	request = checkTransactionChannel(request);
    	Map<String, String> nvp = ParameterAnnotationHelper.mapObjectToNvp(request);
        final String encryptedRequest = iSecurityHandler.encrypt(ParameterAnnotationHelper.createNvpString(nvp));
        return encryptedRequest;
    }

    // FIXME Convert the constructor to builder pattern
    private PaymentRequest checkTransactionChannel(PaymentRequest request) {
		if ((request.transactionChannel).isEmpty() || request.transactionChannel == null) {
			request.setTransactionChannel("Web Online");
		}
		return request;
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
    public PaymentPageResponse unpackResponse(String encodedResponseString) {

        String decodedResponse = iSecurityHandler
                .decrypt(encodedResponseString);

        Map<String, String> nvpMap = ParseUtil.parseWithEscape(decodedResponse,
                '=', ';');

        return createPaymentPageResponse(nvpMap);
    }
}
