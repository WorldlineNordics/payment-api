package com.digitalriver.worldpayments.api.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Util for parsing.
 *
 * &lt;Name&gt; may not contain '=', but it is allowed in &lt;Value&gt;. Any ';' in Name
 * or &lt;Value&gt; must be escaped as ';;'.
 *
 *
 */
public class ParseUtil {

    /**
     * Escapes aChar in a string
     *
     * @see #parseWithEscape(String, char, char)
     *
     * @param aString
     *            the string
     * @param aChar
     *            the character to escape
     * @return aString with aChar escaped
     * @throws IllegalArgumentException
     *             if aString is null
     */
    public static String escapeChar(String aString, char aChar) {
        char[] chars = aString.toCharArray();
        StringBuffer result = new StringBuffer(aString.length() + 8);
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == aChar) {
                result.append(c);
            }
            result.append(c);
        }
        return result.toString();
    }

    private static List<String> parseTokensWithEscape(String aString,
            char aSeparator) {
        char[] chars = aString.toCharArray();
        List<String> result = new ArrayList<String>();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == aSeparator) {
                // Is this an escaped separator value?
                if ((i + 1) < chars.length && chars[i + 1] == aSeparator) {
                    // Remove escape
                    buf.append(chars[i++]);
                    continue;
                }
                result.add(buf.toString());
                buf = new StringBuffer();
            } else {
                buf.append(chars[i]);
            }
        }

        if (buf.length() > 0) {
            result.add(buf.toString());
        }
        return result;
    }

    /**
     * Parse a string of separated name-value pairs with delimiter-escaping. <br>
     * <br>
     * E.g. with aSeparator='=', aDelimiter=';'<br>
     * <i>NAME1=VALUE1;name2=value2;;more;</i> <br>
     * Returns a map with:<br>
     * <i>NAME1</i> -&gt; <i>VALUE1</i><br>
     * <i>name2</i> -&gt; <i>value2;more</i><br>
     *
     * @param aNvpString
     *            a string to parse
     * @param aSeparator
     *            separator char between name and value
     * @param aDelimiter
     *            delimiter between name-value pairs
     * @return a map of parsed name-value pairs
     * @throws IllegalArgumentException
     *             aNvpString is null or format is incorrect
     */
    public static Map<String, String> parseWithEscape(String aNvpString,
            char aSeparator, char aDelimiter) throws IllegalArgumentException {
        String trimmedStr = aNvpString.trim();

        List<String> tokens;
        Map<String, String> result = new HashMap<String, String>();

        if (trimmedStr.length() > 0
                && !trimmedStr.endsWith(Character.toString(aDelimiter))) {
            throw new IllegalArgumentException(
                    "Invalid format, expected trimmed string to terminate with '"
                            + aDelimiter + "'");
        }

        tokens = parseTokensWithEscape(trimmedStr, aDelimiter);

        for (Iterator<String> iterator = tokens.iterator(); iterator.hasNext();) {
            String token = iterator.next();
            int breakPoint = token.indexOf(aSeparator);
            if (breakPoint < 1) {
                throw new IllegalArgumentException("Invalid NVP: '" + token
                        + "'. Must have form <param>" + aSeparator + "<value>"
                        + aDelimiter);
            }
            String str = token.substring(0, breakPoint);
            String name = str == null ? "" : str.trim();
            String value = token.substring(breakPoint + 1);
            if (name.length() == 0) {
                throw new IllegalArgumentException("Invalid NVP: '" + token
                        + "'. Must have non-empty name.");
            }
            result.put(name, value);
        }
        return result;
    }
}
