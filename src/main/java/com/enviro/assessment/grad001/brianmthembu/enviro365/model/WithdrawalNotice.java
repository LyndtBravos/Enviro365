package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@Getter
@AllArgsConstructor
public class WithdrawalNotice {

	private final String name;
	private final int productID;
	private final double withdrawalAmount;
	private final Date withdrawalDate;
	private final Date statementDate;
	private final int investorID;

}