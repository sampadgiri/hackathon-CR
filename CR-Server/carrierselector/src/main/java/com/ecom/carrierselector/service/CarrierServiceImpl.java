package com.ecom.carrierselector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.carrierselector.domain.CarrierDetails;
import com.ecom.carrierselector.domain.OrderDetails;
import com.ecom.carrierselector.repository.CarrierDao;

@Service
public class CarrierServiceImpl implements CarrierService {
	@Autowired
	CarrierDao carrierDao;
	@Override
	public List<CarrierDetails> getAllCarriers() {
		return carrierDao.getAllCarriers();
	}
	@Override
	public List<OrderDetails> getAllOrders() {
		return carrierDao.getAllOrders();
	}

}
