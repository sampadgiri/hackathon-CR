package com.ecom.carrierselector.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceOrder {
	private String CustName;
	private String City;
	private String CustType;
	@JsonProperty("CustName")
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	@JsonProperty("City")
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	@JsonProperty("CustType")
	public String getCustType() {
		return CustType;
	}
	public void setCustType(String custType) {
		CustType = custType;
	}
}
