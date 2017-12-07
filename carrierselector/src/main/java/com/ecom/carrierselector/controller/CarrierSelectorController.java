package com.ecom.carrierselector.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.carrierselector.domain.CarrierDetails;
import com.ecom.carrierselector.domain.OrderDetails;
import com.ecom.carrierselector.domain.XPODetails;
import com.ecom.carrierselector.exception.CSException;
import com.ecom.carrierselector.service.CarrierService;

@RestController
public class CarrierSelectorController {

	@Autowired
	CarrierService carrierService;

	@RequestMapping(value = "/getDetails")
	public @ResponseBody Map<String, Object> getDetails() {
		Map<String, Object> result = new HashMap<>();
		result.put("status", HttpStatus.OK);
		result.put("message", "Successful.");
		return result;
	}

	@RequestMapping(value = "/getAllCarriers")
	public @ResponseBody List<CarrierDetails> getAllCarriers() {
		return carrierService.getAllCarriers();
	}

	@RequestMapping(value = "/getAllOrders")
	public @ResponseBody List<OrderDetails> getAllOrders() {
		return carrierService.getAllOrders();
	}

	@RequestMapping(value = "/carriersByCity/{cityName}")
	public @ResponseBody Map<String, Object> carriersByCity(@PathVariable("cityName") String city) throws CSException {
		Map<String, Object> result = new HashMap<>();
		XPODetails xpd = carrierService.getXPODetailsByCity(city);
		List<CarrierDetails> cd = carrierService.getCarriersByCity(city);
		if ((xpd == null || xpd.getCity() == null) && (cd == null || cd.isEmpty() || cd.size() == 0))
			throw new CSException(HttpStatus.NOT_FOUND.value(), "Service not available at " + city + ".");
		if (xpd.getCity() == null)
			xpd.setCity(city);
		result.put("xpo", xpd);
		result.put("other", cd);
		return result;
	}
}
