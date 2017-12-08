package com.ecom.carrierselector.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XPODetails {
	private String city;
	private int capacity;
	@JsonProperty("City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonProperty("Capacity")
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
