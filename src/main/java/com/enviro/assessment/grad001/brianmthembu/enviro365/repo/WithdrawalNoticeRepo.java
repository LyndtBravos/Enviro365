package com.enviro.assessment.grad001.brianmthembu.enviro365.repo;

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
public class WithdrawalNoticeRepo
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

	static class NoticeRowMapper implements RowMapper<WithdrawalNotice> {
		@Override
		public WithdrawalNotice mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new WithdrawalNotice(rs.getString("name"), rs.getInt("productID"), rs.getDouble("withdrawalAmount"),
										rs.getDate("withdrawalDate"), rs.getDate("statementDate"), rs.getInt("investorID"));
		}
	}

	public List<WithdrawalNotice> findAll() {
		return jdbc.query("select * from notice", new NoticeRowMapper());
	}

	public List<WithdrawalNotice> findByInvestorId(int id) {
		return jdbc.query("select * from notice where investorId=?",
						  new NoticeRowMapper(), id);
	}

	public String insert(WithdrawalNotice notice) {
		int result = jdbc.update("insert into notice (name, productID, withdrawalAmount, withdrawalDate, statementDate, investorID) " + "values(?,?,?,?,?,?)",
								 notice.getName(), notice.getProductID(), notice.getWithdrawalAmount(), notice.getWithdrawalDate(), notice.getStatementDate(),
								 notice.getInvestorID());

		String stringReturn;
		if(result > 0)
			stringReturn = "Withdrawal Notice Addition was a success";
		else stringReturn = "Withdrawal Notice Addition failed, please try again";

		return stringReturn;
	}

	public String delete(int id) {
		int result = jdbc.update("delete from notice where noticeID=?", id);

		String stringReturn;
		if(result > 0)
			stringReturn = "Withdrawal Notice Deletion was a success";
		else stringReturn = "Withdrawal Notice Deletion failed, please try again";

		return stringReturn;
	}
}
