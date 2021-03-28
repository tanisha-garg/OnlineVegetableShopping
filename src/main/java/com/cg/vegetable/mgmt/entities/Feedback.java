package com.cg.vegetable.mgmt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Feedback {
	
	@GeneratedValue
	@Id
	private int feedbackId;
	private int custId;
	private int vegetableId;
	
	private int rating;
	private String comments;
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public int hashCode() {
		return feedbackId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (feedbackId != other.feedbackId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", custId=" + custId + ", vegetableId=" + vegetableId
				+ ", rating=" + rating + ", comments=" + comments + "]";
	}
	
	
	
}
