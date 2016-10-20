package com.digitalriver.worldpayments.api;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.digitalriver.worldpayments.api.utils.Parameter;


public class AbstractPaymentPageRequestTest {

    @Test
    public void testIsClassAnnotationsCorrect() {
        Set<String> shortNames = new HashSet<String>();
        for (Field field : AbstractPaymentPageRequest.class
                .getDeclaredFields()) {
            Parameter param = field.getAnnotation(Parameter.class);

            if ( param == null)
                continue;

            if ( !shortNames.add(param.shortName() ) )
                throw new IllegalArgumentException("field "+field.getName()+ " has short name "+param.shortName()
                        +" which has been used before");

        }
    }
}
