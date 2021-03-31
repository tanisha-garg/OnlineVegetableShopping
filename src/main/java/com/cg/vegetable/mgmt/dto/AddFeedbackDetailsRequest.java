package com.cg.vegetable.mgmt.dto;

import com.cg.vegetable.mgmt.entities.Feedback;

public class AddFeedbackDetailsRequest {
 private Feedback fdbk;

public Feedback getFdbk() {
	return fdbk;
}

public void setFdbk(Feedback fdbk) {
	this.fdbk = fdbk;
}
}
