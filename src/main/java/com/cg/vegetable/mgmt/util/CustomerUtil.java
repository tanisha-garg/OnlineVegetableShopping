package com.cg.vegetable.mgmt.util;


import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerUtil {
	public CustomerDetails toDetail(Customer customer) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.getCustomerId();
		customer.getName();
		customer.getMobileNumber();
		customer.getEmailId();
		customer.getAddress().getFlatNo();
		customer.getAddress().getBuildingName();
		customer.getAddress().getArea();
		customer.getAddress().getCity();
		customer.getAddress().getState();
		customer.getAddress().getPincode();
		return customerDetails;
	}
}