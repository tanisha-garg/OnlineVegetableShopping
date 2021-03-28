package com.cg.vegetable.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vegetable.mgmt.entities.Feedback;

public interface IFeedbackRepository extends JpaRepository<Feedback ,Integer> {

//	public Feedback addFeedback(Feedback fdk);
//	public List<Feedback> viewAllFeedbacks(int vegetableId);

}
