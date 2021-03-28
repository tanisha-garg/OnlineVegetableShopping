package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Feedback;

public interface IFeedbackService {

	public Feedback addFeedback(Feedback fdk);
	public List<Feedback> viewAllFeedbacks(int vegetableId);
	public List<Feedback> viewFeedbacks(int customerId);

}
