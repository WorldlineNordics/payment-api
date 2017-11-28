package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.utils.ParseUtil;

import java.util.Map;

public class NvpUtil {
    public static String createNvpString(Map<String, String> nvp) {
        StringBuilder nvpString = new StringBuilder();
        for (String name : nvp.keySet()) {
            nvpString.append(name).append("=").append(ParseUtil.escapeChar(nvp.get(name), ';')).append(";");
        }
        return nvpString.toString();
    }
}
