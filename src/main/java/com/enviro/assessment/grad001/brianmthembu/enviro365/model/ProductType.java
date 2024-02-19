package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ProductType {
	RETIREMENT("Retirement"),
	SAVINGS("Savings");

	private final String displayName;
}
