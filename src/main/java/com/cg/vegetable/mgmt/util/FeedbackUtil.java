package com.cg.vegetable.mgmt.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.dto.FeedbackDetails;
import com.cg.vegetable.mgmt.entities.Feedback;

@Component
public class FeedbackUtil {
	
	public FeedbackDetails toDetail(Feedback feedback) {
		FeedbackDetails feedbackDetails = new FeedbackDetails();
		feedbackDetails.setFeedbackId(feedback.getFeedbackId());
		feedbackDetails.setCustId(feedback.getCustId());
		feedbackDetails.setVegetableId(feedback.getVegetableId());
		feedbackDetails.setRating(feedback.getRating());
		feedbackDetails.setComment(feedback.getComments());
		return feedbackDetails;
	}
	
	public List<FeedbackDetails> toFeedbackDetails(List<Feedback> feedbackList){
		List<FeedbackDetails> desiredList = new ArrayList<>();
		for(Feedback feedback: feedbackList) {
			desiredList.add(toDetail(feedback));
		}
		return desiredList;
	}

}
