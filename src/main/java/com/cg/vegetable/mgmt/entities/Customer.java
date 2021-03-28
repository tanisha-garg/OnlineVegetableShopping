package com.cg.vegetable.mgmt.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private int customerId;

	@OneToOne(cascade = {CascadeType.ALL})
	private Address address;
	
	private String name;
	private String mobileNumber;
	private String emailId;
	private String password;
	private String confirmPassword;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Cart cart;
	
	public Customer() {
	}
	
	public Customer(String name, String mobileNumber, String emailId) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailid(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

	
}
