package com.ecom.carrierselector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecom.carrierselector.service.CarrierService;
import com.ecom.carrierselector.service.CarrierServiceImpl;

@SpringBootApplication
public class CarrierselectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrierselectorApplication.class, args);
	}
	
	@Bean
	CarrierService carrierService() {
		return new CarrierServiceImpl();
	}
}
