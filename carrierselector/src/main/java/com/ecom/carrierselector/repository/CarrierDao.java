package com.ecom.carrierselector.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ecom.carrierselector.domain.CarrierDetails;

@Repository
public class CarrierDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<CarrierDetails> getAllCarriers(){
		return jdbcTemplate.query("SELECT * FROM CARRIER_DETAILS", new RowMapper<CarrierDetails>() {

			@Override
			public CarrierDetails mapRow(ResultSet rs, int num) throws SQLException {
				CarrierDetails cd = new CarrierDetails();
				cd.setId(rs.getInt("ID"));
				cd.setCarrierName(rs.getString("CARRIER_NAME"));
				cd.setCarrierRating(rs.getDouble("CARRIER_RATING"));
				return cd;
			}
			
		});
	}
}
