package com.cg.vegetable.mgmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.Feedback;
import com.cg.vegetable.mgmt.exceptions.InvalidFeedbackCommentException;
import com.cg.vegetable.mgmt.exceptions.InvalidFeedbackRatingException;
import com.cg.vegetable.mgmt.repository.IFeedbackRepository;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
	
	@Autowired
	private IFeedbackRepository feedbackRepository;

	@Transactional
	@Override
	public Feedback addFeedback(Feedback fdbk) {
		validateFeedback(fdbk);
        Feedback saved = feedbackRepository.save(fdbk);
        return saved;
		
	}

	@Override
	public List<Feedback> viewAllFeedbacks(int vegetableId) {
		List<Feedback> feedbackList = feedbackRepository.findAll();
		List<Feedback> desiredList = new ArrayList<>();
		for (Feedback feedback:feedbackList) {
			if (feedback.getVegetableId()==vegetableId) {
				desiredList.add(feedback);
				
			}
		}
		return desiredList;
	}

	@Override
	public List<Feedback> viewFeedbacks(int custId) {
		List<Feedback> feedbackList = feedbackRepository.findAll();
		List<Feedback> desiredList = new ArrayList<>();
		for (Feedback feedback:feedbackList) {
			if (feedback.getCustId()==custId) {
				desiredList.add(feedback);
				
			}
		}
		return desiredList;
	}
	
	public void validateFeedback (Feedback fdbk)
	{
		validateComment(fdbk.getComments());
		validateRating(fdbk.getRating());	
		
	}
    public void validateComment (String comment) {
    	if (comment==null) {
    		throw new InvalidFeedbackCommentException ("Comment can't be null ");
    }}
    	 public void validateRating (int rating) {
    	    	if (rating<0) {
    	    		throw new InvalidFeedbackRatingException ("Invalid rating");
    	    }
}}
