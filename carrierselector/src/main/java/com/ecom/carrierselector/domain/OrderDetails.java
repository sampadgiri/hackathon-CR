package com.ecom.carrierselector.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDetails {
	private String orderNumber;
	private String custName;
	private String city;
	private String custType;
	private String carrierName;
	private String status;
	@JsonProperty("OrderNo")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@JsonProperty("CustName")
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	@JsonProperty("City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonProperty("CustType")
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	@JsonProperty("Carrier")
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	@JsonProperty("Status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
