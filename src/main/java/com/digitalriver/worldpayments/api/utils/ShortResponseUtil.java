package com.digitalriver.worldpayments.api.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Map;

import com.digitalriver.worldpayments.api.ParameterAnnotationHelper;
import com.digitalriver.worldpayments.api.PaymentPageShorterResponse;
import com.digitalriver.worldpayments.api.security.SecurityHandlerException;
import com.digitalriver.worldpayments.api.security6.JKSKeyHandlerV6;
import com.digitalriver.worldpayments.api.utils.CryptoUtils.CryptoException;

public class ShortResponseUtil {

	private static final String ENCODING_UTF_8 = "UTF-8";
	public static final byte RSA_2048_AES_128_ENC_MODE_V6 = 6;

	private Base64Utils iBase64Encoder;
	private JKSKeyHandlerV6 iKeyHandlerV6;

	public ShortResponseUtil(JKSKeyHandlerV6 iKeyHandlerV6) {
		this.iKeyHandlerV6 = iKeyHandlerV6;
		this.iBase64Encoder = new Base64Utils();
	}

	public String decodeWithBase64(String PpsResponse) {
		byte[] envelope;
		byte[] netgiroCertSerialNo = new byte[4];
		byte[] signature = new byte[256];
		envelope = iBase64Encoder.decode(PpsResponse);
		byte[] cipherText = new byte[envelope.length - 261];
		PublicKey drwpPublicKey;

		if (envelope[0] == 6) {
			System.arraycopy(envelope, 1, netgiroCertSerialNo, 0, 4);
			System.arraycopy(envelope, 5, signature, 0, 256);
			System.arraycopy(envelope, 261, cipherText, 0, cipherText.length);
			drwpPublicKey = iKeyHandlerV6.getDrwpPublicKey(new BigInteger(netgiroCertSerialNo).longValue());

			try {
				if (!CryptoUtils.verifySignature(drwpPublicKey, cipherText, signature)) {
					throw new SecurityHandlerException("Signature does not match!");
				}
			} catch (CryptoException e) {
				throw new SecurityHandlerException("Failed to verify signature!", e);
			}
		}

		try {
			return new String(cipherText, ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new SecurityHandlerException("Failed to convert result to UTF-8", e);
		}
	}

	public static PaymentPageShorterResponse createShorterPaymentResponse(String decodedResponse) {

		Map<String, String> nvpMap = ParseUtil.parseWithEscape(decodedResponse, '=', ';');

		PaymentPageShorterResponse ppShorterResponse = new PaymentPageShorterResponse();
		try {
			ParameterAnnotationHelper.mapNvpToObject(ppShorterResponse, nvpMap);
		} catch (Exception e) {
			throw new IllegalArgumentException("Contact Worldline support", e);
		}

		return ppShorterResponse;
	}

}
