package com.ecom.carrierselector.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XPODetails {
	private String city;
	private int vehicalCount;
	private int capacity;
	private int available;
	@JsonProperty("City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonProperty("VehicalCount")
	public int getVehicalCount() {
		return vehicalCount;
	}
	public void setVehicalCount(int vehicalCount) {
		this.vehicalCount = vehicalCount;
	}
	@JsonProperty("Capacity")
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@JsonProperty("Available")
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
}
