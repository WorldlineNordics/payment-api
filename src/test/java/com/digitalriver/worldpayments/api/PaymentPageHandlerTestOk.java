package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.security5.DERKeyHandler;
import com.digitalriver.worldpayments.api.security5.SecurityHandlerImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertTrue;

public class PaymentPageHandlerTestOk extends PaymentPageHandlerTestBase {

    @Test
    public void testHandlerUnpackResponse() {

        SecurityHandlerImpl impl = new SecurityHandlerImpl(
                new DERKeyHandler(new File("src/test/resources/drwp_key.der"),
                        new File("src/test/resources/merchant_pub.der")));
        System.err
                .println(impl
                        .encrypt("A=123456789;C=123;E=OrderId12345;F=2011-03-31 14:04:15 CEST;T=true;N=4711;Q=Visa;W=01/14;V=xxxx xxxx xxxx 4711;"));

        // String responseString =
        // "Bf090jUvOxI6kjlYzyoWRrPkrTIva_W775runQOBgSrtTCNIqsgW8uoagGf_xz1UNKO5Jfj272NSTv2bDwF6CVQvNy1D9U5donhYjWgmM-c9BO0eB0ss3i16qTHRE-n8ptocHjBFFlpza-Qg_TiRULefTbzYRLVsYxf2RGEUZwVNJAmEQN8t7w_Lgy3jOASMVjXDXElYZMImyBCfsjA4DqI_ZwF3wAPrHO4KEZ7E8plXvTJRRaeR6qumHWP0FUdC5J7b21HMyVEwxGQXf76bmlwQs1VvaPfkDMl8MhQC0Kks57M-4_Jk-uhZRGA16bDvQVrp-C9yPsC9EnlIgwIUggNt2xJQM1Z9wzcxv50W6AS6c0CCcaKGNaYvXO0I5n0O3x3tKSG7Upw9vA0M2zWB5nOcBukdRfbHVp7_DgLOYhOZg8eAzUwqJNuZJ7qH-r34GlGgxNHSS6zidpOMfTBXD0Mc9r_kby1UBi2oBWJK4V8JyFR0_Lf_2MNyaeMbgoWpDA==";
        String responseString = "Bf090jUvOxI6kjlYzyoWRrPkrTIva_W775runQOBgSrtT5gThXEytl8fkzE5XWpCK8AUupd44bMmYYh8QDaFETALDt3CzeIcM_-naW93GeLLPPmlYPdTbnE7yX8YbjLYJDyF0weV9dMpVXtTmtVlXSqkHCQsIYiR31ptHlp556b7-qUlrSkYV6m0f3rrB77kqDz7cLnpjiC2YKft4DKdVBMgaf-MzTZvGQali-DwNGsGq-D3DH7ZqXZ1icBJxj1SP_eLt0imWYYeqUl2HAWMfH17Q5lz523dzhMC9uVxzqQISC8U4BhL4Jjubeejl-nwuI5DNv7VZ46pfTllAzxO7XNu1fFdL0vs0ARzwNjF_fOzxHTG2HgSlsj2DyCyzAvnSf5H_dC03W9l0glwKS7AhsHvLrB4FZcMVHT5H_n-Vsa_LOS36qd5KjEf9RE00pNshfvimMxapNWc86_MeSzd8BI9dvS0MnSLtlel5UHNSGyr_3BLQ3RwyPoDy-cC0kVZL0SFOgZZO2GL4iwdn9Bd4zJ-lY-0j3PGCxpltb46Hz7W";

        PaymentPageResponse response = paymentPageHandler
                .unpackResponse(responseString);

        Assert.assertEquals(123456789L, (long) response.getMid());
        Assert.assertEquals(true, response.wasRedirected());
        Assert.assertEquals("01/14", response.getTokenizationResult().getStoreExpirationDate());
        Assert.assertEquals("xxxx xxxx xxxx 4711", response.getTokenizationResult().getStoreMaskedCardNumber());
        Assert.assertEquals("4711", response.getTokenizationResult().getToken());

    }

    @Test
    public void testUnpackRequest() {
        File privateKeyFile = new File(PaymentPageHandlerTestOk.class.getResource("/merchant_key.der").getFile());
        File publicKeyFile = new File(PaymentPageHandlerTestOk.class.getResource("/merchant_pub.der").getFile());;
        File ngPrivateKeyFile = new File(PaymentPageHandlerTestOk.class.getResource("/drwp_key.der").getFile());
        File ngPublicKeyFile = new File(PaymentPageHandlerTestOk.class.getResource("/drwp_pub.der").getFile());

        PaymentPageRequest request = createValidRequest();

        SecurityHandlerImpl encrypter = new SecurityHandlerImpl(
                new DERKeyHandler(privateKeyFile, ngPublicKeyFile));
        Map<String, String> nvp = ParameterAnnotationHelper.mapObjectToNvp(request);
        String encrypted = encrypter.encrypt(NvpUtil.createNvpString(nvp));

        SecurityHandlerImpl decrypter = new SecurityHandlerImpl(
                new DERKeyHandler(ngPrivateKeyFile, publicKeyFile));

        String decrypted = decrypter.decrypt(encrypted);
        Map<String, String> nvpm = ParameterAnnotationHelper.mapObjectToNvp(request);
        Assert.assertEquals(NvpUtil.createNvpString(nvpm), decrypted);
    }

    @Test
    public void testHandlerCreateRequestString_LineItems() {
        PaymentPageRequest request = createValidRequest();
        LineItem li = new LineItem();
        li.id = "1";
        li.amount = 47.11;
        li.quantity = 2;
        LineItem li2 = new LineItem();
        li2.id = "3";
        li2.amount = 53.11;
        li2.quantity = 2;
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(li);
        lineItems.add(li2);
        request.lineItems = lineItems;
        request.shippingCompanyName = "MyCompany";
        request.autoCapture = false;

        // Construct a set from the actual result
        Map<String, String> nvp = ParameterAnnotationHelper.mapObjectToNvp(request);
        String requestString = NvpUtil.createNvpString(nvp);
        Set<String> resultSet = new HashSet<String>();
        String[] requestParams = requestString.split(";");
        for (String s : requestParams) {
            resultSet.add(s);
        }

        // Construct expected request parameter set
        String[] expectedRequestParams = { "J=100.0", "T=SE", "U=sv", "K=SEK", "LIC_1=47.11", "LIA_1=1", "LID_1=2", "LIC_2=53.11", "LIA_2=3", "LID_2=2", "A=123456789", "G=OrderId",
                "C=SE", "V=http://merchant.com", "BG=MyCompany", "B=1", "D=Web Online", "AAQ=false" };
        Set<String> expectedResultSet = new HashSet<String>();
        for (String s : expectedRequestParams) {
            expectedResultSet.add(s);
        }

        Assert.assertEquals(expectedResultSet, resultSet);

    }

    @Test
    public void testHandlerCreateRequestString_OK_MapData() {
        PaymentPageRequest request = createValidRequest();
        Map<String, String> additional = new HashMap<String, String>();
        additional.put("2", "B");
        additional.put("1", "A");
        request.additionalParameters = additional;
        request.paymentMethodId = 1;
        Map<String, String> nvp = ParameterAnnotationHelper.mapObjectToNvp(request);
        String requestString = NvpUtil.createNvpString(nvp);
        String addParamOut = requestString.substring(requestString.indexOf("Y=") + 2);
        addParamOut = addParamOut.substring(0, addParamOut.indexOf(";"));
        List<String> VALID_VALUES = Arrays.asList("2=B#1=A#", "1=A#2=B#");
        assertTrue(VALID_VALUES.contains(addParamOut));
    }

    @Test
    public void testUnpackResponseOAT() {

        SecurityHandlerImpl impl = new SecurityHandlerImpl(
                new DERKeyHandler(new File("src/test/resources/drwp_key.der"),
                        new File("src/test/resources/merchant_pub.der")));
        System.err
                .println(impl
                        .encrypt("A=123456789;C=123;E=OrderId12345;F=2011-03-31 14:04:15 CEST;T=true;N=4711;Q=Visa;W=01/14;V=xxxx xxxx xxxx 4711;OAT=SAVINGS_ACCOUNT;"));

        String responseString = "Bf090jUvOxI6kjlYzyoWRrPkrTIva_W775runQOBgSrtOwjJ6lK41N2ZiEpy4b3IK7GZmtkX65tIRqZeUsFOFUqiubJXAMY3yAnZjlM1meK0FBFBpnTcAs9drMv5QQ5wqYO3e4wCCQC8mnuFSscOnj3LGSkbRry2rnVlto-SZZLVFWWKZkdaYvOemcz9LqQ3Vlw9wcGxNltShBh-BQ_f85xRpzWjeS_xAk1_QAXXgmHPv1O2C_pe7v-RirPwhem0B1s3i-8BOS9KfXIEifVPfpcPO1H714lBK-WgPy19D0C70pqJG6h4ZsPkD-tvc0QwTu8nHx0438EIezmF7UWWJmWAO9HcwTge8MXSTXksqNIsmbDT2Mv8JwUueMeQtsJQFWtRr506ygGqeuYhWpcMSrdxpqpbvbfziAdhQDGRuk4LOypDrWIjJAHDKebPEF7JfOAOTHxqcdXACCxV2faktVwZZOy615Uhrrq1amXwLhgxsvmVE6N0_Eyom1T8bHzNh_Ni0BL_EbxrItqcLva0jCWtKjfRw6r-ihW5v8ZN820TUuVSJGw8BiaZYjlpmcJb5w==";

        PaymentPageResponse response = paymentPageHandler
                .unpackResponse(responseString);

        Assert.assertEquals(123456789L, (long) response.getMid());
        Assert.assertEquals(true, response.wasRedirected());
        Assert.assertEquals("01/14", response.getTokenizationResult().getStoreExpirationDate());
        Assert.assertEquals("xxxx xxxx xxxx 4711", response.getTokenizationResult().getStoreMaskedCardNumber());
        Assert.assertEquals("4711", response.getTokenizationResult().getToken());
        Assert.assertEquals("SAVINGS_ACCOUNT", response.getOriginatingAccountType());
    }
}
