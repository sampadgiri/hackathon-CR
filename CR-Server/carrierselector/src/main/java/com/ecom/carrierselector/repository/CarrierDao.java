package com.ecom.carrierselector.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ecom.carrierselector.domain.CarrierDetails;
import com.ecom.carrierselector.domain.OrderDetails;

@Repository
public class CarrierDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<CarrierDetails> getAllCarriers() {
		return jdbcTemplate.query("SELECT * FROM CARRIER_DETAILS", new RowMapper<CarrierDetails>() {

			@Override
			public CarrierDetails mapRow(ResultSet rs, int num) throws SQLException {
				CarrierDetails cd = new CarrierDetails();
				cd.setCity(rs.getString("CITY"));
				cd.setCarrName(rs.getString("CARRIER_NAME"));
				cd.setRating(rs.getDouble("CARRIER_RATING"));
				return cd;
			}

		});
	}

	public List<OrderDetails> getAllOrders() {
		return jdbcTemplate.query("SELECT * FROM ORDER_DETAILS", new RowMapper<OrderDetails>() {

			@Override
			public OrderDetails mapRow(ResultSet rs, int num) throws SQLException {
				OrderDetails od = new OrderDetails();
				od.setOrderNumber(rs.getString("ORDER_NMBR"));
				od.setCustName(rs.getString("CUST_NAME"));
				od.setCity(rs.getString("CITY"));
				od.setCustType(rs.getString("CUST_TYPE"));
				od.setCarrierName(rs.getString("CARRIER"));
				return od;
			}
		});
	}
}
