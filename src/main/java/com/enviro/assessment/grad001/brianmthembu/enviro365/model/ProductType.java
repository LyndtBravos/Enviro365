package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ProductType {
	RETIREMENT("Retirement"),
	SAVINGS("Savings");

	private final String displayName;

	ProductType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
