package com.cg.vegetable.mgmt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.dto.FeedbackDetails;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Feedback;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.service.IFeedbackService;
import com.cg.vegetable.mgmt.util.FeedbackUtil;

@RequestMapping("/feedback")
@RestController
public class FeedbackRestController {
	@Autowired
	private IFeedbackService feedbackService;
	
	@Autowired
	private FeedbackUtil feedbackUtil;
	
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping ("/add")
	public String addFeedback(@RequestBody FeedbackDetails requestData)
	{
		
		Customer customer = customerService.viewCustomer(requestData.getCustId());
		Feedback feedback = new Feedback(requestData.getFeedbackId(),requestData.getVegetableId(),requestData.getRating(),requestData.getComment());
		Feedback feedbackAdded = feedbackService.addFeedback(feedback);
		return "Feedback added successfully for customer ID"+feedbackAdded.getCustId();
	}
	
	@GetMapping("/viewAll")
	public List<FeedbackDetails> fetchAllFeedbacks(@RequestBody FeedbackDetails requestData) {
		List<Feedback> feedbackList = feedbackService.viewAllFeedbacks(requestData.getVegetableId());
		List<FeedbackDetails> allFeedbackDetails = feedbackUtil.toFeedbackDetails(feedbackList);
		return allFeedbackDetails;
			}
	
	@GetMapping("/view")
	public List<FeedbackDetails> fetchFeedbacks(@RequestBody FeedbackDetails requestData) {
		List<Feedback> feedbackList = feedbackService.viewFeedbacks(requestData.getCustId());
		List<FeedbackDetails> feedbackDetails = feedbackUtil.toFeedbackDetails(feedbackList);
		return feedbackDetails;
			}

}
