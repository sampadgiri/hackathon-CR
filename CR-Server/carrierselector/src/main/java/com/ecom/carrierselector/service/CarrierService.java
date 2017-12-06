package com.ecom.carrierselector.service;

import java.util.List;

import com.ecom.carrierselector.domain.CarrierDetails;
import com.ecom.carrierselector.domain.OrderDetails;

public interface CarrierService {
	public List<CarrierDetails> getAllCarriers();
	public List<OrderDetails> getAllOrders();
}
