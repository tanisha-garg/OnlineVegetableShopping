package com.cg.vegetable.mgmt.util;

import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.entities.Customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CustomerUtil {
	public CustomerDetails toDetail(Customer customer) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId(customer.getCustomerId());
		customerDetails.setName(customer.getName());
		customerDetails.setMobileNumber(customer.getMobileNumber());
		customerDetails.setEmailId(customer.getEmailId());

		if (customer.getAddress() != null) {
			customerDetails.setFlatNo(customer.getAddress().getFlatNo());
			customerDetails.setBuildingName(customer.getAddress().getBuildingName());
			customerDetails.setArea(customer.getAddress().getArea());
			customerDetails.setCity(customer.getAddress().getCity());
			customerDetails.setState(customer.getAddress().getState());
			customerDetails.setPincode(customer.getAddress().getPincode());
		}

		return customerDetails;
	}
	public List<CustomerDetails> toDetailList(List<Customer> list){
		List<CustomerDetails> desiredList = new ArrayList<>();
		for(Customer customer:list) {
			desiredList.add(toDetail(customer));
			
		}
		return desiredList;
	}
}