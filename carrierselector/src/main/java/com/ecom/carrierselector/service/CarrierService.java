package com.ecom.carrierselector.service;

import java.util.List;

import com.ecom.carrierselector.domain.CarrierDetails;
import com.ecom.carrierselector.domain.OrderDetails;
import com.ecom.carrierselector.domain.PlaceOrder;
import com.ecom.carrierselector.domain.XPODetails;

public interface CarrierService {
	public List<CarrierDetails> getAllCarriers();
	public List<OrderDetails> getAllOrders();
	public List<CarrierDetails> getCarriersByCity(String city);
	public XPODetails getXPODetailsByCity(String city);
	public String placeOrder(PlaceOrder order);
}
