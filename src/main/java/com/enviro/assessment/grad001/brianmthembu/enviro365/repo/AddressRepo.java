package com.enviro.assessment.grad001.brianmthembu.enviro365.repo;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AddressRepo {

	JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource());

	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:enviroinvestments");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	static class AddressRowMapper implements RowMapper<Address> {
		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Address(rs.getString("street"), rs.getString("city"),
							   rs.getString("province"), rs.getString("country"), rs.getInt("investorID"));
		}
	}

	public List<Address> findAll() {
		return jdbcTemplate.query("select * from address", new AddressRowMapper());
	}

	public List<Address> findByInvestorID(int id) {
		return jdbcTemplate.query("select * from address where investorID=?",
								  new AddressRowMapper(), id);
	}

	public ResponseEntity<String> insert(Address address) {
		int result =  jdbcTemplate.update("insert into address(street, city, province, country, investorID)" + "values(?,?,?,?,?)",
		   address.getStreet(), address.getCity(), address.getProvince(), address.getCountry(), address.getInvestorID());

		String stringReturn;
		if(result > 0)
			stringReturn = "Address Addition was a success";
		else stringReturn = "Address Addition failed, please try again";

		return stringReturn.contains("failed") ? new ResponseEntity<>(stringReturn, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(stringReturn, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> delete(int id) {
		int result = jdbcTemplate.update("delete from address where addressID=?", id);
		String stringReturn;
		if(result > 0)
			stringReturn = "Address Deletion was a success";
		else stringReturn = "Address Deletion failed, please try again";

		return stringReturn.contains("failed") ? new ResponseEntity<>(stringReturn, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(stringReturn, HttpStatus.ACCEPTED);

	}


}
