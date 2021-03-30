package com.cg.vegetable.mgmt.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.dto.BillingDetailsDTO;
import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.BillingDetails;

@Component
public class BillingDetailsUtil {
	
	public BillingDetailsDTO toDetails(BillingDetails bill) {
		BillingDetailsDTO details = new BillingDetailsDTO();
		
		LocalDateTime date = bill.getTransactionDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String formattedDateString = date.format(formatter);
		
		details.setBillingId(bill.getBillingId());
		details.setTransactionMode(bill.getTransactionMode());
		details.setTransactionDate(formattedDateString);
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
