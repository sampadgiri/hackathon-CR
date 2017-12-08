package com.ecom.carrierselector.repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class UpdateOrderProcedure extends StoredProcedure {

	public UpdateOrderProcedure(JdbcTemplate jdbcTemplate, String name) {
		super(jdbcTemplate, name);
		setParameters(new SqlParameter("v_order_nmbr", Types.VARCHAR));
		setParameters(new SqlParameter("v_city", Types.VARCHAR));
		setParameters(new SqlParameter("v_prevCarrier", Types.VARCHAR));
		setParameters(new SqlParameter("v_carrier", Types.VARCHAR));
		compile();
	}
	
	public void update(String orderNumber, String city, String prevCarrier, String carrier) {
		Map<String, Object> input = new HashMap<>();
		input.put("v_order_nmbr", orderNumber);
		input.put("v_city", city);
		input.put("v_prevCarrier", prevCarrier);
		input.put("v_carrier", carrier);
		execute(input);
	}
}
