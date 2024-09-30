package com.enviro.assessment.grad001.brianmthembu.enviro365.repo;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Address;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Contact;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Investor;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Product;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.WithdrawalNotice;
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
public class InvestorRepo {

	public JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource());

	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:enviroinvestments");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	static class InvestorRowMapper implements RowMapper<Investor> {
		@Override
		public Investor mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Investor(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("amount"));
		}
	}

	public ResponseEntity<String> increaseFunds(String name, double amount) {
		if(amount < 0)
			return new ResponseEntity<>("Amount passed is negative, please retry", HttpStatus.FORBIDDEN);

		Investor investor = findByName(name);
		if(investor == null)
			return new ResponseEntity<>("Investor doesn't exist, please retry", HttpStatus.BAD_REQUEST);

		double newBalance = investor.getAmount() + amount;

		String stringReturned = "";
		int result = jdbcTemplate.update("update investor set amount = ? where name = ?",
										 newBalance, investor.getName());
		if(result > 0)
			stringReturned = "Funds have been increased to: R" + newBalance;

		return stringReturned.equals("") ? new ResponseEntity<>("Something went wrong with the transaction, please retry", HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(stringReturned, HttpStatus.OK);
	}

	public ResponseEntity<String> withdrawFunds(String name, double amount) {
		if(amount < 0)
			return new ResponseEntity<>("Amount passed is negative, please retry", HttpStatus.FORBIDDEN);

		Investor investor = findByName(name);
		if(investor == null)
			return new ResponseEntity<>("Investor doesn't exist, please retry", HttpStatus.BAD_REQUEST);

		if(!investor.canBeWithdrew(amount))
			return new ResponseEntity<>("Investor doesn't have enough funds for this, please retry", HttpStatus.BAD_REQUEST);
		double newBalance = investor.getAmount() - amount;

		String stringReturned = "";
		int result = jdbcTemplate.update("update investor set amount=? where name=?",
										 newBalance, investor.getName());
		if(result > 0)
			stringReturned = "Funds have been reduced to: R" + newBalance;

		return stringReturned.equals("") ? new ResponseEntity<>("Something went wrong with the transaction, please retry", HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(stringReturned, HttpStatus.OK);
	}

	public List<Investor> findAll() {
		List<Investor> list = jdbcTemplate.query("select * from investor", new InvestorRowMapper());
		if(!list.isEmpty())
			for(Investor investor: list)
				fillOtherProps(investor);
		else{
			return null;
		}
		return list;
	}

	private void fillOtherProps(Investor investor) {
		int id = investor.getId();
		investor.setAddress(new AddressRepo().findByInvestorID(id));
		investor.setContact(new ContactRepo().findByInvestorID(id));
		investor.setProduct(new ProductRepo().findByInvestorID(id));
		investor.setNotice(new WithdrawalNoticeRepo().findByInvestorId(id));
	}

	public Investor findByName(String name) {
		List<Investor> list = jdbcTemplate.query("select * from investor where name=?",
																   new InvestorRowMapper(), name);
		if(!list.isEmpty()){
			for(Investor investor: list)
				fillOtherProps(investor);
		} else{
			return null;
		}

		return list.get(0);
	}

	public ResponseEntity<String> insert(Investor investor) {
		int result = jdbcTemplate.update("insert into investor (name, age, amount)" + "values(?,?,?)",
								   investor.getName(), investor.getAge(), investor.getAmount());

		String stringReturn;
		if(result > 0)
			stringReturn = "Investor Creation was a success";
		else stringReturn = "Investor Creation failed, please try again";

		return stringReturn.contains("failed") ? new ResponseEntity<>(stringReturn, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(stringReturn, HttpStatus.ACCEPTED);
	}
}