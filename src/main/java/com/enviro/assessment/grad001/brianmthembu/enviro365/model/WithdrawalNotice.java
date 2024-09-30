package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@Getter
public class WithdrawalNotice {

	private final String name;
	private final int productID;
	private final double withdrawalAmount;
	private final Date withdrawalDate;
	private final Date statementDate;
	private final int investorID;

	public WithdrawalNotice(String name, int productID, double withdrawalAmount, Date withdrawalDate, Date statementDate, int investorID) {
		this.name = name;
		this.productID = productID;
		this.withdrawalAmount = withdrawalAmount;
		this.withdrawalDate = withdrawalDate;
		this.statementDate = statementDate;
		this.investorID = investorID;
	}

	public String getName() {
		return name;
	}

	public int getProductID() {
		return productID;
	}

	public double getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public Date getWithdrawalDate() {
		return withdrawalDate;
	}

	public Date getStatementDate() {
		return statementDate;
	}

	public int getInvestorID() {
		return investorID;
	}
}