package com.enviro.assessment.grad001.brianmthembu.enviro365.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Investor {

	private Integer id;
	private String name;
	private int age;
	private double amount;
	private List<Address> address;
	private List<Contact> contact;
	private List<Product> product;
	private List<WithdrawalNotice> notice;

	public Investor(int id, String name, int age, double amount) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.amount = amount;
		address = new ArrayList<>();
		contact = new ArrayList<>();
		product = new ArrayList<>();
		notice = new ArrayList<>();
	}

	public boolean canBeWithdrew(double amount) {
		double canBeWithdrew = getAmount() * .9;
		return amount < canBeWithdrew;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getAmount() {
		return amount;
	}

	public List<Address> getAddress() {
		return address;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public void setNotice(List<WithdrawalNotice> notice) {
		this.notice = notice;
	}
}
