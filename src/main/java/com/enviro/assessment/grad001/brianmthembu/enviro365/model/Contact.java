package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
public class Contact {

	private final String type;
	private final String value;
	private final int investorID;

	public Contact(String type, String value, int investorID) {
		this.type = type;
		this.value = value;
		this.investorID = investorID;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public int getInvestorID() {
		return investorID;
	}
}
