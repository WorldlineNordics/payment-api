package com.netgiro.pp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Txt2ResourceBundle {

	// Microsux Excel calls x-UTF-16LE-BOM for Unicode text format
	private static CharsetDecoder decoder = Charset.forName("x-UTF-16LE-BOM")
			.newDecoder();

	private static final String COL_SEPARATOR = "\t";
	private static final String ROW_SEPARATOR = "\r\n";

	private static final String RESOURCE_FOLDER = "Templates/context/";
	private static final String RESOURCE_NAME = "strings";
	private static final String INPUT_FILE = "/Translations_x-UTF-16LE-BOM.txt";

	public Txt2ResourceBundle() throws IOException {

		Map<String, Map<Locale, String>> result = readFromFile(INPUT_FILE);
		Map<Locale, FileOutputStream> files = new HashMap<Locale, FileOutputStream>();

		// Create all files.
		List<String> keys = new ArrayList<String>(result.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			Map<Locale, String> translation = result.get(key);
			for (Locale locale : translation.keySet()) {
				FileOutputStream fos = files.get(locale);
				if (fos == null) {
					File file = new File(
						RESOURCE_FOLDER + RESOURCE_NAME
							+ (locale.toString().length() > 0 ? "_" : "")
							+ locale.toString() + ".properties" );
					System.out.println("Creating file " + file.getAbsolutePath());
					fos = new FileOutputStream(file);
					files.put(locale, fos);
				}
				String trans = translation.get(locale);
				if (trans != null && !trans.isEmpty()) {
					fos.write(key.getBytes());
					fos.write("=".getBytes());
					// Removing default start and end double quotes from the property value
					if(trans.startsWith("\"") &&  trans.endsWith("\"")){
						fos.write((trans.substring(1, trans.length()-1)).getBytes());
					}else{
						fos.write(trans.getBytes());
					}
					fos.write(ROW_SEPARATOR.getBytes());
				}
			}
		}
	}

	private Map<String, Map<Locale, String>> readFromFile(String fileName)
			throws IOException {

		// key, locale, string
		Map<String, Map<Locale, String>> result = new HashMap<String, Map<Locale, String>>();

		// locales in correct order
		List<Locale> locales = new ArrayList<Locale>();

		InputStreamReader reader = new InputStreamReader(Txt2ResourceBundle.class.getResourceAsStream(INPUT_FILE), decoder);

		BufferedReader in = new BufferedReader(reader);

		// First row is header
		{
			String header = in.readLine();
			String cols[] = header.split(COL_SEPARATOR);
			for (String col : cols) {
				if (col.length() > 0) {
					String a = col.substring(col.lastIndexOf(" ") + 1);
					String lang = a.replace("'", "");
					Locale locale;
					if (lang.length() == 0) {
						locale = Locale.ROOT;
					} else {
						if (lang.contains("_")) {
							String[] langCountry = lang.split("_");
							locale = new Locale(langCountry[0], langCountry[1]);
						} else {
							locale = new Locale(lang);
						}
					}
					locales.add(locale);
				}
			}
		}

		// next comes the translations
		String row = null;
		do {
			row = in.readLine();
			if (row != null) {
				String cols[] = row.split(COL_SEPARATOR);
				if ( cols.length>0 && cols[0].equals("static.eftPaymentInstructionRefThreatDK"))
					System.err.println(trimQuatation(cols[2]));
				Map<Locale, String> translation = new HashMap<Locale, String>();
				// next are translations
				int i = 1;
				for (Locale locale : locales) {
					if (cols.length > i)
						translation.put(locale, native2Ascii(cols[i]));
					i++;
				}
				// first column is keys
				result.put(cols[0], translation);
			}
		} while (row != null);

		return result;
	}

	private String trimQuatation(String string) {
		return string.replaceAll("\"\\w", "").replaceAll("\"\"", "\"");
	}

	private static String native2Ascii(String str) {
		StringBuffer sb = new StringBuffer(str.length());
		sb.setLength(0);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			sb.append(native2Ascii(c));
		}
		return (new String(sb));
	}

	private static StringBuffer native2Ascii(char charater) {
		StringBuffer sb = new StringBuffer();
		if (charater > 255) {
			sb.append("\\u");
			int lowByte = (charater >>> 8);
			sb.append(int2HexString(lowByte));
			int highByte = (charater & 0xFF);
			sb.append(int2HexString(highByte));
		} else {
			sb.append(charater);
		}
		return sb;
	}

	private static String int2HexString(int code) {
		String hexString = Integer.toHexString(code);
		if (hexString.length() == 1)
			hexString = "0" + hexString;
		return hexString;
	}

	public static void main(String[] args) throws IOException {
		new Txt2ResourceBundle();
	}
}
