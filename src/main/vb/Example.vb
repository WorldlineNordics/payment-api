Imports com.digitalriver.worldpayments.api
Imports com.digitalriver.worldpayments.api.security4

'depends on PaymentPageAPI.dll, IKVM.OpenJDK.Core.dll, IKVM.Runtime.dll, jni also

Module PaymentPageNetAPI
    Sub Main()
        Dim jks As JKSKeyHandler = New JKSKeyHandler("C:\Workspace\PaymentPageAPI\src\resources_test\testkeys\merchant.jks",
      "111111", "merchant", "ngcert")
        Dim ppHandler As PaymentPageHandler = New PaymentPageHandler(PaymentPageHandler.DEFAULT_TEST_BASE_URL, jks)
        Dim request As PaymentPageRequest = New PaymentPageRequest()
        request.setMid(java.lang.Long.valueOf(11111111))
        request.setTransactionChannel("Web Online")
        request.setOrderId("MyBestOrderId")
        request.setAmount(java.lang.Double.valueOf(100.0))
        request.setCurrency("SEK")
        request.setConsumerLanguage("sv")
        request.setConsumerCountry("SE")
        request.setRedirectReturnUrl("http://url")
        Dim str = ppHandler.createRedirectUrl(request)
        Console.WriteLine(str)
        Console.ReadLine()
    End Sub
End Module