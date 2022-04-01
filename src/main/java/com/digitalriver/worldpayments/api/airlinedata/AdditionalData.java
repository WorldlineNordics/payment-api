package com.digitalriver.worldpayments.api.airlinedata;

import java.util.List;

public class AdditionalData {

	private String mid;
	private String orderId;
	private List<AirlineData> airlineData;
	
	public AdditionalData(String mid, String orderId, List<AirlineData> airlineDataList) {
		super();
		this.mid = mid;
		this.orderId = orderId;
		this.airlineData = airlineDataList;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<AirlineData> getAirlineData() {
		return airlineData;
	}

	public void setAirlineData(List<AirlineData> airlineData) {
		this.airlineData = airlineData;
	}

	@Override
	public String toString() {
		return "AdditionalData [mid=" + mid + ", orderId=" + orderId + ", airlineData=" + airlineData + "]";
	}
	
}
