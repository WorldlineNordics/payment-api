package com.digitalriver.worldpayments.api.utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class ObjectConverter {

	public static Object convertObject(Class<?> clazz, String value)
			throws Exception {
		if (clazz.isAssignableFrom(Date.class)) {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss z", Locale.ROOT);
			return sdf.parse(value);
		}
		if (clazz.isAssignableFrom(Integer.TYPE)
				|| clazz.isAssignableFrom(Integer.class)) {
			return new Integer(value);
		}
		if (clazz.isAssignableFrom(Long.TYPE)
				|| clazz.isAssignableFrom(Long.class)) {
			return new Long(value);
		}
		if (clazz.isAssignableFrom(Double.TYPE)
				|| clazz.isAssignableFrom(Double.class)) {
			return new Double(decimalSafe(value));
		}
		if (clazz.isAssignableFrom(Boolean.TYPE)
				|| clazz.isAssignableFrom(Boolean.class)) {
			return new Boolean(value);
		}
		if (clazz.isAssignableFrom(Byte.TYPE)
				|| clazz.isAssignableFrom(Byte.class)) {
			return new Byte(value);
		}
		if (clazz.isAssignableFrom(byte[].class)) {
			return new BigInteger(value).toByteArray();
		}
		if (clazz.isArray() && clazz.isAssignableFrom(byte[].class)) {
			return new BigInteger(value).toByteArray();
		}
		if (clazz.equals(Map.class)) {
			return ParseUtil.parseWithEscape(value, '=', '#');
		}
		return value;
	}

	private static String decimalSafe(String val) {
		return val.replace(",", ".");
	}


}
