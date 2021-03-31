package com.cg.vegetable.mgmt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.dto.AddFeedbackDetailsRequest;
import com.cg.vegetable.mgmt.dto.FeedbackDetails;
import com.cg.vegetable.mgmt.entities.Feedback;
import com.cg.vegetable.mgmt.service.IFeedbackService;
import com.cg.vegetable.mgmt.util.FeedbackUtil;

@RequestMapping("/feedback")
@RestController
public class FeedbackRestController {
	@Autowired
	private IFeedbackService feedbackService;
	
	@Autowired
	private FeedbackUtil feedbackUtil;
	
	@PostMapping ("/add")
	public FeedbackDetails addFeedback(@RequestBody AddFeedbackDetailsRequest requestData)
	{
		Feedback feedback = new Feedback();
		feedback.setCustId(requestData.getCustId());
		feedback.setVegetableId(requestData.getVegetableId());
		feedback.setRating(requestData.getRating());
		feedback.setComments(requestData.getComment());		
		Feedback feedbackAdded = feedbackService.addFeedback(feedback);
		FeedbackDetails details = feedbackUtil.toDetail(feedbackAdded);
		return details;
	}
	
	@GetMapping("/view/veg/{id}")
	public List<FeedbackDetails> fetchAllFeedbacks(@PathVariable("id") int vegId) {
		List<Feedback> feedbackList = feedbackService.viewAllFeedbacks(vegId);
		List<FeedbackDetails> allFeedbackDetails = feedbackUtil.toFeedbackDetails(feedbackList);
		return allFeedbackDetails;
		
	}
	
	@GetMapping("/view/customer/{id}")
	public List<FeedbackDetails> fetchFeedbacks(@PathVariable("id") int customerId) {
		List<Feedback> feedbackList = feedbackService.viewFeedbacks(customerId);
		List<FeedbackDetails> feedbackDetails = feedbackUtil.toFeedbackDetails(feedbackList);
		return feedbackDetails;
			}

}
