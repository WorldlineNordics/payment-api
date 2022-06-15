package com.digitalriver.worldpayments.api.utils;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class ShortResponseUtil {

//	private Base64Utils iBase64Encoder;
	
	public void decodeWithBase64(String PPSResponse, PublicKey publicKey, Signature sign)
	{
//		iBase64Encoder.decode(PPSResponse);
		byte[] response = PPSResponse.getBytes();
		Base64.getDecoder().decode(response);
		
//		CryptoUtils.verifySignature(publicKey, response, sign);
	}
}
