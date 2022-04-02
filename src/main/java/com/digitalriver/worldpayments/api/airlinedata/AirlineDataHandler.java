package com.digitalriver.worldpayments.api.airlinedata;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AirlineDataHandler {
	
	private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	private final String adsUrl;
	
	public AirlineDataHandler(String adsUrl) {
		this.adsUrl = adsUrl;
	}

	public HttpPost createHttpRequest(AdditionalData additionalData, String mid, String orderId, String token) throws Exception {
		String endpoint = adsUrl + "/merchants/" + mid + "/orders/" + orderId + "/additionaldata";
		final HttpPost httpPost = new HttpPost(endpoint);
		configureHttpRequest(token, httpPost);
		HttpEntity entity = new StringEntity(gson.toJson(additionalData));
		httpPost.setEntity(entity);
		return httpPost;	
	}

	private void configureHttpRequest(String token, final HttpRequestBase httpRequest) {
		httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		httpRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
		httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
		httpRequest.setConfig(requestConfig);
	}

}
