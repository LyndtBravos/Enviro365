package com.enviro.assessment.grad001.brianmthembu.enviro365.repo;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Product;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.ProductType;
import com.enviro.assessment.grad001.brianmthembu.enviro365.validator.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepo {

	JdbcTemplate jdbc = new JdbcTemplate(mysqlDataSource());

	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:enviroinvestments");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	static class ProductRowMapper implements RowMapper<Product>
	{
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			String type = rs.getString("type");
			boolean var = false;
			for(var i : ProductType.values())
				if(i.getDisplayName().equals(type)){
					var = true;
					break;
				}
			return !var ? null : new Product(rs.getString("name"), type,
				 rs.getDouble("balance"), rs.getInt("investorID"));
		}
	}

	public List<Product> findAll() {
		return jdbc.query("select * from product", new ProductRowMapper());
	}

	public List<Product> findByInvestorID(int id) {
		return jdbc.query("select * from product where investorID=?",
						  new ProductRowMapper(), id);
	}

	public ResponseEntity<String> insert(Product product) {
		boolean var = new ProductValidator().validateProductType(product);

		if(var)
			return new ResponseEntity<>("Investor cannot purchase this product", HttpStatus.BAD_REQUEST);

		int result = jdbc.update("insert into product (name,type,balance,investorID)" + "values(?,?,?,?)",
						   product.getName(), product.getProductType(), product.getBalance(), product.getInvestorID());

		String stringReturn;
		if(result > 0)
			stringReturn = "Product Addition was a success";
		else stringReturn = "Address Addition failed, please try again";

		return stringReturn.contains("failed") ? new ResponseEntity<>(stringReturn, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(stringReturn, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> delete(int id) {
		int result = jdbc.update("delete from product where productID=?", id);

		String stringReturn;
		if(result > 0)
			stringReturn = "Product Deletion was a success";
		else stringReturn = "Product Deletion failed, please try again";

		return stringReturn.contains("failed") ? new ResponseEntity<>(stringReturn, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(stringReturn, HttpStatus.ACCEPTED);
	}

}
