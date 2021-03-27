package com.cg.vegetable.mgmt.dto;

public class UpdateVegetableTypeRequest {
	private int vegId;
	private String type;
	public int getVegId() {
		return vegId;
	}
	public void setVegId(int vegId) {
		this.vegId = vegId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
