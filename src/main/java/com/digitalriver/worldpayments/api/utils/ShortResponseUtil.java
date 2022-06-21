package com.digitalriver.worldpayments.api.utils;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import com.digitalriver.worldpayments.api.security.SecurityHandlerException;
import com.digitalriver.worldpayments.api.utils.CryptoUtils.CryptoException;

public class ShortResponseUtil {

	private Base64Utils iBase64Encoder;
	
	public String decodeWithBase64(String PPSResponse, PublicKey publicKey, byte[] sign) throws CryptoException 
	{
//		iBase64Encoder.decode(PPSResponse);
		byte[] response = PPSResponse.getBytes();
		byte[] decodedResponse = Base64.getDecoder().decode(response);
		String plainText;
		try {
			CryptoUtils.verifySignature(publicKey, response, sign);
		} catch (CryptoException e) {
			throw e;
		}
		try {
			 plainText = decodedResponse.toString();
		} catch (Exception excep) {
			// Should never happen
			throw excep;
		}
		return plainText;
	}
}
