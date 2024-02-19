package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
@AllArgsConstructor
public class Address {

	private final String street;
	private final String city;
	private final String province;
	private final String country;
	private final int investorID;

}
