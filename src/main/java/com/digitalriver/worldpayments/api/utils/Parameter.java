package com.digitalriver.worldpayments.api.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {

    public static class Validate {

        public static void checkLength(String val, Parameter param,
                String paramName) {
            if (param.maxLength() > 0 && val.length() > param.maxLength())
                throw new IllegalArgumentException("Parameter " + paramName
                        + " is too long can be max " + param.maxLength()
                        + " but is " + val.length());
        }

        public static void checkRequired(String val, Parameter param,
                String paramName) {
            if (param.required() && isBlank(val))
                throw new IllegalArgumentException(
                        "Missing required value for " + paramName);
        }

        private static boolean doEvalRegEx(Parameter param, String val) {
            boolean hasRegEx = param.regEx() != null
                    && !param.regEx().equals("*");
            boolean hasValue = !isBlank(val);
            return hasRegEx && hasValue;
        }

        public static void evalRegEx(String val, Parameter param,
                String paramName) {
            if (doEvalRegEx(param, val)) {
                if (!val.matches(param.regEx()))
                    throw new IllegalArgumentException("Parameter " + paramName
                            + " with value " + val + " does not match pattern "
                            + param.regEx());
            }
        }

        public static boolean isBlank(String str) {
            return str == null || str.trim().length() == 0;
        }

    }

    int maxLength() default 0;

    String regEx() default "*";

    boolean required() default false;

    String shortName();

}
