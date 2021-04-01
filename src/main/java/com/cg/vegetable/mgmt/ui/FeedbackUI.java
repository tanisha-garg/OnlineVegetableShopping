package com.cg.vegetable.mgmt.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Feedback;
import com.cg.vegetable.mgmt.service.IFeedbackService;

@Component
public class FeedbackUI {

	@Autowired
	IFeedbackService feedbackService;

	public void start() {
		Feedback f1 = new Feedback();
		f1.setCustId(1);
		f1.setVegetableId(3);
		f1.setRating(3);
		f1.setComments("good");

		Feedback f2 = new Feedback();
		f2.setCustId(1);
		f2.setVegetableId(5);
		f2.setRating(10);
		f2.setComments("DAYUUUM!");
		feedbackService.addFeedback(f1);
		feedbackService.addFeedback(f2);
		displayFeedback(f1);
		List<Feedback> list = feedbackService.viewFeedbacks(1);
		for(Feedback feedback:list) {
			displayFeedback(feedback);
			
		}
	}

	

	private void displayFeedback(Feedback f) {
		System.out.println(f.getRating() + " " + f.getComments());

	}
	
		}

	
	
 

