package com.cg.vegetable.mgmt.dto;

import javax.validation.constraints.NotBlank;

public class UpdateVegetableNameRequest {
	private int vegId;
	
	private String name;
	public int getVegId() {
		return vegId;
	}
	public void setVegId(int vegId) {
		this.vegId = vegId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
