package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
public class Product {

	private final String name;
	private final String productType;
	private final double balance;
	private final int investorID;

	public Product(String name, String productType, double balance, int investorID) {
		this.name = name;
		this.productType = productType;
		this.balance = balance;
		this.investorID = investorID;
	}

	public String getName() {
		return name;
	}

	public String getProductType() {
		return productType;
	}

	public double getBalance() {
		return balance;
	}

	public int getInvestorID() {
		return investorID;
	}
}
