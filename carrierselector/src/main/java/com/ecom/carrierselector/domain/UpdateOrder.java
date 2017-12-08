package com.ecom.carrierselector.domain;

public class UpdateOrder {
	private String orderNumber;
	private String city;
	private String prevCarrier;
	private String carrier;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPrevCarrier() {
		return prevCarrier;
	}
	public void setPrevCarrier(String prevCarrier) {
		this.prevCarrier = prevCarrier;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
}
