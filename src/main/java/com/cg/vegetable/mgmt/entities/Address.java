package com.cg.vegetable.mgmt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {


	@GeneratedValue
	@Id
	private int addressId;
	private String flatNo;
	private String buildingName;
	private String area;
	private String city;
	private String state;
	private String pincode;
	
	public Address() {
		
	}
	
	public Address(String flatNo, String buildingName, String area, String city, String state, String pincode) {
		this.flatNo = flatNo;
		this.buildingName = buildingName;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public int getAddressId() {
	   return addressId;
	    }
	  
	  public void setaddressId(int addressId) {
	   this.addressId=addressId; 
	   }
	 
	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	

}
