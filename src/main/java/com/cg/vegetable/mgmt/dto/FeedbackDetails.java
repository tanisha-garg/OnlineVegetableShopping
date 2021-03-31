package com.cg.vegetable.mgmt.dto;

public class FeedbackDetails {
	
	private int feedbackId;
	private int custId;
	private int vegetableId;
	private int rating;
	private String comment;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getVegetableId() {
		return vegetableId;
	}

	public void setVegetableId(int vegetableId) {
		this.vegetableId = vegetableId;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feeedbackId) {
		this.feedbackId = feeedbackId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
