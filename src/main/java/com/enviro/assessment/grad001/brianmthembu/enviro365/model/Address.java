package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
public class Address {

	private final String street;
	private final String city;
	private final String province;
	private final String country;
	private final int investorID;

	public Address(String street, String city, String province, String country, int investorID) {
		this.street = street;
		this.city = city;
		this.province = province;
		this.country = country;
		this.investorID = investorID;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getProvince() {
		return province;
	}

	public String getCountry() {
		return country;
	}

	public int getInvestorID() {
		return investorID;
	}

}
