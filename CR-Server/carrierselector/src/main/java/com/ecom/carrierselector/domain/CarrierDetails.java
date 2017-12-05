package com.ecom.carrierselector.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarrierDetails {
	private String City;
	private String CarrName;
	private double Rating;
	@JsonProperty(value="City")
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	@JsonProperty(value="CarrName")
	public String getCarrName() {
		return CarrName;
	}
	public void setCarrName(String carrName) {
		CarrName = carrName;
	}
	@JsonProperty(value="Rating")
	public double getRating() {
		return Rating;
	}
	public void setRating(double rating) {
		Rating = rating;
	}
}
