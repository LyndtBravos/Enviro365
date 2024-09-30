package com.enviro.assessment.grad001.brianmthembu.enviro365.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Contact;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ContactRepo
{
	JdbcTemplate jdbc = new JdbcTemplate(mysqlDataSource());

	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:enviroinvestments");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	static class ContactRowMapper implements RowMapper<Contact> {
		@Override
		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Contact(rs.getString("contactType"), rs.getString("contactValue"), rs.getInt("investorID"));
		}
	}

	public List<Contact> findAll() {
		return jdbc.query("select * from contact",
					  	new ContactRowMapper());
	}

	public List<Contact> findByInvestorID(int id) {
		return jdbc.query("select * from contact where investorID=?",
						  			new ContactRowMapper(), id);
	}

	public String insert(Contact contact) {
		int result = jdbc.update("insert into contact(contactType, contactValue, investorID) values(?,?,?)",
								 contact.getType(), contact.getValue(), contact.getInvestorID());

		String stringReturned;
		if(result > 0)
			stringReturned = "Contact Addition was a success.";
		else stringReturned = "Contact Addition failed, please try again.";

		return stringReturned;
	}

	public String delete(int id) {
		int result = jdbc.update("delete from contact where contactId=?", id);

		String stringReturn;
		if(result > 0)
			stringReturn = "Contact Deletion was a success.";
		else stringReturn = "Contact Deletion failed, please try again.";

		return stringReturn;

	}

}
