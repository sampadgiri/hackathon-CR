package com.ecom.carrierselector.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ecom.carrierselector.domain.CarrierDetails;
import com.ecom.carrierselector.domain.OrderDetails;
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
				return cd;
			}

		});
	}

	public List<CarrierDetails> getCarriersByCity(String city) {

		// return jdbcTemplate.query("SELECT * FROM CARRIER_DETAILS WHERE CITY=?", new
		// Object[] { city },
		return jdbcTemplate.query(
				"SELECT C.CITY AS CITY, C.CARRIER_NAME AS CARRIER_NAME, C.CARRIER_RATING AS CARRIER_RATING, C.CAPACITY AS CAPACITY, O.USED AS USED FROM CARRIER_DETAILS C LEFT OUTER JOIN (SELECT CITY, CARRIER, COUNT(1) AS USED FROM ORDER_DETAILS WHERE STATUS<>'DELIVERED' GROUP BY CITY, CARRIER) O ON C.CITY=O.CITY AND C.CARRIER_NAME = O.CARRIER WHERE C.CITY=?",
				new Object[] { city }, new RowMapper<CarrierDetails>() {

					@Override
					public CarrierDetails mapRow(ResultSet rs, int num) throws SQLException {
						CarrierDetails cd = new CarrierDetails();
						cd.setCity(rs.getString("CITY"));
						cd.setCarrName(rs.getString("CARRIER_NAME"));
						cd.setRating(rs.getDouble("CARRIER_RATING"));
						cd.setCapacity(rs.getInt("CAPACITY"));
						int used = 0;
						try {
							used = rs.getInt("USED");
						}catch(SQLException se) {
							
						}
						cd.setAvailable(cd.getCapacity()-used);
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
				"SELECT CITY, NO_OF_VEHICALS, CAPACITY, (NO_OF_VEHICALS*CAPACITY-(SELECT COUNT(1) FROM ORDER_DETAILS WHERE STATUS<>'DELIVERED' AND CARRIER='XPO' AND CITY=?)) AS AVAILABLE FROM XPO_DETAILS WHERE CITY=?",
				new Object[] { city, city }, new ResultSetExtractor<XPODetails>() {

					@Override
					public XPODetails extractData(ResultSet rs) throws SQLException, DataAccessException {
						XPODetails xd = new XPODetails();
						while (rs.next()) {
							xd.setCity(rs.getString("CITY"));
							xd.setVehicalCount(rs.getInt("NO_OF_VEHICALS"));
							xd.setCapacity(rs.getInt("CAPACITY"));
							xd.setAvailable(rs.getInt("AVAILABLE"));
						}
						return xd;
					}

				});
	}
	
	public String placeOrder(OrderDetails order) {
		KeyHolder kh = new GeneratedKeyHolder();
		String query = "INSERT INOT ORDER_DETAILS (ORDER_NMBR, CUST_NAME, CITY, CUST_TYPE, CARRIER, STATUS) VALUES('OR'||LPAD(ORDER_NUMBER.NEXTVAL,3,'0'),?,?,?,?,'CREATED')";
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query, new String[] {"ORDER_NMBR"});
				ps.setString(1, order.getCustName());
				ps.setString(2, order.getCity());
				ps.setString(3, order.getCustType());
				ps.setString(4, order.getCarrierName());
				return ps;
			}
		}, kh);
		return kh.getKey().toString();
	}
}
