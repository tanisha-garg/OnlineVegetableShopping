package com.cg.vegetable.mgmt.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.dto.BillingDetailsResponse;
import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.BillingDetails;

@Component
public class BillingDetailsUtil {
	
	@Autowired
	private DateUtil dateUtil;
	
	public BillingDetailsResponse toDetails(BillingDetails bill) {
		BillingDetailsResponse details = new BillingDetailsResponse();
		
		String dateTimeText = dateUtil.toText(bill.getTransactionDate());
		
		details.setBillingId(bill.getBillingId());
		details.setTransactionMode(bill.getTransactionMode());
		details.setTransactionDate(dateTimeText);
		details.setTransactionStatus(bill.getTransactionStatus());
		Address address = bill.getAddress();
		if(address != null) {
			details.setFlatNo(address.getFlatNo());
			details.setBuildingName(address.getBuildingName());
			details.setArea(address.getArea());
			details.setCity(address.getCity());
			details.setState(address.getState());
			details.setPincode(address.getPincode());
		}
		
		return details;
	}

}
