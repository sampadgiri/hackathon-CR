package com.ecom.carrierselector.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.carrierselector.domain.CarrierDetails;
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
}
