package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
@AllArgsConstructor
public class Product {

	private String name;
	private String productType;
	private double balance;
	private int investorID;

}
