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
        String responseString = "Bf090jUvOxI6kjlYzyoWRrPkrTIva_W775runQOBgSrtgLsDNC_1YO0qJZRItUhVuDYKFUnr97rjRHDRP6QTzF65zEeF2gvRe-foOarnQxXpSI26SV8VJ_g54KxrBf0aUjLA77VwcsasZ0z9_B99qesKNQj0XF1squHuDr6vX8gA0KnahmzbeQ9juaN9eHqdAZzgXl6VF1Q-qN-GGqfMne-OcNvFURpfmNgENKltFK_AII7_xPfHIf68Z2_e1hvLCwCuwcMrn4bOQXxc9UR3A9TEwvxjiUyizjpiRptMWSVPajcChN1OKHvSV_d6v_ONHBUpuJrdwKng35OBJScmcMqiwHTL069z4ge3tSIovQpYFdSamJhIcdiQcZtjXcPbMiCv5oCSouh4gWWf7n2Ntj__d0WY6IdyxvxvTpoFPp0li5sqarf8xSrWRQDx36iFhgA1R_EY_9Fqr1E3lj0Jl5PJMiVI1woqTxv24NjpByTMwIDFeR230e4pnGBu7X8Yt_o897GnZmTLnvnKy5HF4DTDy-E0ehNImJoH-D7XHKxG";

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
        String[] expectedRequestParams = { "J=100.0", "T=SE", "U=sv", "K=SEK", "ADB=false", "LIC_1=47.11", "LIA_1=1", "LID_1=2", "LIC_2=53.11", "LIA_2=3", "LID_2=2", "A=123456789", "G=OrderId",
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

        String responseString = "Bf090jUvOxI6kjlYzyoWRrPkrTIva_W775runQOBgSrtK6sIy7j7VFVK4mEiHkHJ7KowWUCo01BW7Y9fqdCOMk-rcZyEmDyc1jaECFO3JkOfWzn3_5acWeK8ZV_m8idiJXFRXNvAxserqZrjObEruYBwFoRjxuWiw2Go5_NnrGTb3iiWpOFtXw8yUo7daZymsNECFaujbpxCQvAtgq4ij2U9jf7huaw5ETlHkFSyLjTNlxwFR4rJ6qn7rtj1WnTy5cv9r2UyoUNdWp1JNO_TyxOr83WVYwx8UwdCQF_qC4p1EpJeAqfgVWy-EYGMWQJ2NUzbHYYYsnQGsZCUXlPeMl3PlbTkRxG5Zc_IQp3GWzcA88zs0Re3O1t-E1PBix0eUso8xr03TaMuIX6plibjNLgtmC270iv_JnMQNB-JtgDnkjcNEQ81Hzp6iWS1i5fZ8JCwQzKT_lRnoqFS7SJcqIFetSBqruxA0x4P8j4l_Qg4uKCzRk9OxNrlyKZGsxT0-ww2Ic26IFJGsn3CE147n16BmZtd6IjdCUoR-mvJG7F0tT5Pvz-yFeOHpQPNmtUTrw==";

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
