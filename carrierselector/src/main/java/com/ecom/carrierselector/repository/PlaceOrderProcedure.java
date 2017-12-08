package com.ecom.carrierselector.repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class PlaceOrderProcedure extends StoredProcedure {

	public PlaceOrderProcedure(JdbcTemplate jdbcTemplate, String name) {
		super(jdbcTemplate, name);
		setParameters(new SqlParameter("v_custName", Types.VARCHAR));
		setParameters(new SqlParameter("v_city", Types.VARCHAR));
		setParameters(new SqlParameter("v_custType", Types.VARCHAR));
		setParameters(new SqlOutParameter("o_orderId", Types.VARCHAR));
		compile();
	}

	public String insertRecord(String custName, String city, String custType){
		String result = "";
		Map<String, Object> input = new HashMap<>();
		input.put("v_custName", custName);
		input.put("v_city", city);
		input.put("v_custType", custType);
		Map<String, Object> res = execute(input);
		result = (String) res.get("o_orderId");
		return result;
	}
}
