package com.ecom.carrierselector.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ecom.carrierselector.domain.CarrierDetails;
import com.ecom.carrierselector.domain.OrderDetails;
import com.ecom.carrierselector.domain.PlaceOrder;
import com.ecom.carrierselector.domain.UpdateOrder;
import com.ecom.carrierselector.domain.XPODetails;

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
				cd.setCapacity(rs.getInt("CAPACITY"));
				return cd;
			}

		});
	}

	public List<CarrierDetails> getCarriersByCity(String city) {
		return jdbcTemplate.query(
				"SELECT * FROM CARRIER_DETAILS WHERE CARRIER_NAME<>'XPO Logistics' AND CITY=?",
				new Object[] { city }, new RowMapper<CarrierDetails>() {

					@Override
					public CarrierDetails mapRow(ResultSet rs, int num) throws SQLException {
						CarrierDetails cd = new CarrierDetails();
						cd.setCity(rs.getString("CITY"));
						cd.setCarrName(rs.getString("CARRIER_NAME"));
						cd.setRating(rs.getDouble("CARRIER_RATING"));
						cd.setCapacity(rs.getInt("CAPACITY"));
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
				od.setStatus(rs.getString("STATUS"));
				return od;
			}
		});
	}

	public XPODetails getXPODetailsByCity(String city) {
		return jdbcTemplate.query(
				"SELECT * FROM CARRIER_DETAILS WHERE CARRIER_NAME='XPO Logistics' AND CITY=?",
				new Object[] { city }, new ResultSetExtractor<XPODetails>() {

					@Override
					public XPODetails extractData(ResultSet rs) throws SQLException, DataAccessException {
						XPODetails xd = new XPODetails();
						while (rs.next()) {
							xd.setCity(rs.getString("CITY"));
							xd.setCapacity(rs.getInt("CAPACITY"));
						}
						return xd;
					}

				});
	}
	
	public String placeOrder(PlaceOrder order) {
		PlaceOrderProcedure pOrder = new PlaceOrderProcedure(jdbcTemplate, "carr_rtg_proc_inst");
		return pOrder.insertRecord(order.getCustName(), order.getCity(), order.getCustType());
	}
	
	public List<String> getAllCities() {
		return jdbcTemplate.query("SELECT DISTINCT CITY FROM CARRIER_DETAILS", new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int cnt) throws SQLException {
				return rs.getString(1);
			}});
	}
	
	public void updateOrder(UpdateOrder order) {
		UpdateOrderProcedure uProcedure = new UpdateOrderProcedure(jdbcTemplate, "carr_rtg_proc_updt");
		uProcedure.update(order.getOrderNumber(), order.getCity(), order.getPrevCarrier(), order.getCarrier());
	}
}
