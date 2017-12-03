package com.ecom.carrierselector.domain;

public class CarrierDetails {
	private int id;
	private String carrierName;
	private double carrierRating;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public double getCarrierRating() {
		return carrierRating;
	}
	public void setCarrierRating(double carrierRating) {
		this.carrierRating = carrierRating;
	}
}
