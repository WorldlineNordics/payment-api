package com.digitalriver.worldpayments.api.security5;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.Map;

import com.digitalriver.worldpayments.api.ParameterAnnotationHelper;
import com.digitalriver.worldpayments.api.PaymentPageShorterResponse;
import com.digitalriver.worldpayments.api.security.SecurityHandler;
import com.digitalriver.worldpayments.api.security.SecurityHandlerException;
import com.digitalriver.worldpayments.api.utils.Base64Utils;
import com.digitalriver.worldpayments.api.utils.CryptoUtils;
import com.digitalriver.worldpayments.api.utils.ParseUtil;
import com.digitalriver.worldpayments.api.utils.CryptoUtils.CryptoException;

public abstract class ShortResponseV5Util {

	private static final String ENCODING_UTF_8 = "UTF-8";
	public static final byte RSA_2048_AES_128_ENC_MODE_V6 = 6;
	private KeyHandler iKeyHandler;
	private Base64Utils iEncoder;

	public ShortResponseV5Util(KeyHandler aKeyHandler) {
		iKeyHandler = aKeyHandler;
		iEncoder = new Base64Utils();
	}

	public String decodeWithBase64(String PpsResponse) {
		byte[] envelope;
		byte[] netgiroCertFingerprint = new byte[16];
		byte[] signature = new byte[256];
		byte[] plainText;
		byte[] cipherText;
		PublicKey verifySignKey;

		envelope = iEncoder.decode(PpsResponse);
		cipherText = new byte[envelope.length - 273];

		System.arraycopy(envelope, 1, netgiroCertFingerprint, 0, 16);
		System.arraycopy(envelope, 17, signature, 0, 256);
		System.arraycopy(envelope, 273, cipherText, 0, cipherText.length);
		verifySignKey = iKeyHandler.getPublicKey(netgiroCertFingerprint);

		try {
			if (!CryptoUtils.verifySignature(verifySignKey, cipherText, signature)) {
				throw new SecurityHandlerException("Signature does not match!");
			}
		} catch (CryptoException e) {
			throw new SecurityHandlerException("Failed to verify signature!", e);
		}

		try {
			return new String(envelope, ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// Should never happen
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