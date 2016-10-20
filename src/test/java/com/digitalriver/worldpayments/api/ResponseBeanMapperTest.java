package com.digitalriver.worldpayments.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class ResponseBeanMapperTest {

    @Test
    public void testMap() {
        Map<String, String> nvpMap = new HashMap<String, String>();
        nvpMap.put("A", "123456789");
        nvpMap.put("B", "1");
        nvpMap.put("C", "123");
        nvpMap.put("D", "Card");
        nvpMap.put("F", "2011-03-04 17:38:02 CET");
        nvpMap.put("U", "XXXX XXXX XXXX 1234");
        nvpMap.put("M", "VISA");
        nvpMap.put("AJ", "456789");
        nvpMap.put("AI", "Visa");
        nvpMap.put("G", "4346781016245508");
        nvpMap.put("H", "4444333322221111");
        nvpMap.put("I", "Successful");
        nvpMap.put("J", "3333222211114444");
        nvpMap.put("K", "MAESTRO");
        nvpMap.put("L", "4901234");
        nvpMap.put("Q", "paypal");
        nvpMap.put("R", "654321" );
        nvpMap.put("S", "InitDebit" );
        nvpMap.put("X", "22222222222" );
        nvpMap.put("Y", "www.e.f.t" );
        nvpMap.put("Z", "33333333333" );
        nvpMap.put("AA", "44444444444" );
        nvpMap.put("AB", "55555555555" );

        PaymentPageResponse response = PaymentPageHandler
                .createPaymentPageResponse(nvpMap);
        Assert.assertEquals(123456789L, (long)response.getMid());
        Assert.assertEquals(123L, (long)response.getTransaction().getTransactionId());
        Assert.assertEquals("Fri Mar 04 17:38:02 CET 2011", response.getTimestamp().toString());
        Assert.assertEquals("XXXX XXXX XXXX 1234", response.getMaskedCardNumber().toString());
        Assert.assertEquals("VISA", response.getCardType());
        Assert.assertEquals("456789", response.getAcquirerAuthCode());
        Assert.assertEquals("Visa", response.getPaymentMethodName());
        Assert.assertEquals("4346781016245508", response.getVResId());
        Assert.assertEquals("4444333322221111", response.getPAResId());
        Assert.assertEquals("Successful", response.getdddsStatus());
        Assert.assertEquals("3333222211114444", response.getPOSId());
        Assert.assertEquals("MAESTRO", response.getCardTxType());
        Assert.assertEquals("4901234", response.getCardTxId());
        Assert.assertEquals("paypal", response.getStoreCardType());
        Assert.assertEquals("654321", response.getIbpTxId());
        Assert.assertEquals("InitDebit", response.getIbpTxType());
        Assert.assertEquals("Card", response.getPaymentMethod());
        Assert.assertEquals("22222222222", response.getEftReferenceId());
        Assert.assertEquals("www.e.f.t", response.getEftPaymentSlipUrl());
        Assert.assertEquals(33333333333L, (long)response.getEftTxId());
        Assert.assertEquals(44444444444L, (long)response.getDirectDebitTxId());
        Assert.assertEquals(55555555555L, (long)response.getPayoutTxId());
    }

    @Test
    public void testNull() {
        Map<String, String> nvpMap = new HashMap<String, String>();
        nvpMap.put("B", "1");
        PaymentPageResponse response = PaymentPageHandler
                .createPaymentPageResponse(nvpMap);
        Assert.assertNull(response.getMid());
        Assert.assertNull(response.getTransaction());
        Assert.assertNull(response.getTimestamp());
    }

}
