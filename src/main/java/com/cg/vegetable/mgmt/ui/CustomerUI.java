package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.service.ICustomerService;

@Component
public class CustomerUI {
	
	
	@Autowired
	ICustomerService customerService;
	
	public void start(){
		
		Address address1= new Address();
		Address address2= new Address();
		Address address3= new Address();
		
		address1.setFlatNo("M8");
		address1.setBuildingName("Korattur");
		address1.setArea("Ashok vihar");
		address1.setCity("Chennai");
		address1.setState("Tamil Nadu");
		address1.setPincode("6998086");
		
		address2.setFlatNo("Y178");
		address2.setBuildingName("Anna nagar");
		address2.setArea("Ashok nagar");
		address2.setCity("Chennai");
		address2.setState("Tamil Nadu");
		address2.setPincode("69980342");
		
		address3.setFlatNo("BA3");
		address3.setBuildingName("Vadapalani");
		address3.setArea("Ashok van");
		address3.setCity("Chennai");
		address3.setState("Tamil Nadu");
		address3.setPincode("69982412"); 
		
		
		
		System.out.println("adding Customer");
		
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		Customer customer3 = new Customer();
		
		
		customer1.setName("abeer");
		customer1.setEmailid("abeer@gmail.com");
		customer1.setMobileNumber("abeer100");
		customer1.setPassword("abeer@100");
		customer1.setAddress(address1);
		
		
	
		customer2.setName("abc");
		customer2.setEmailid("abc102@gmail.com");
		customer2.setMobileNumber("abc102");
		customer2.setPassword("abc@102");
		customer2.setAddress(address2);
		
	
		customer3.setName("saurabh");
		customer3.setEmailid("saurabh@gmail.com");
		customer3.setMobileNumber("saurabh.103");
		customer3.setPassword("saurabh@103");
		customer3.setAddress(address3);
		
		customerService.addCustomer(customer1);
		customerService.addCustomer(customer2);
		customerService.addCustomer(customer3);
		
		System.out.println("\n********************* Saved Customers in database *********************\n");
		display(customer1);
		display(customer2);
		display(customer3);
		
		customer1.setName("wolf");
		customer2.setPassword("haha");
		
		customerService.updateCustomer(customer1);
		customerService.updateCustomer(customer2);
		
		Customer fetchedCustomer1= customerService.viewCustomer(customer1.getCustomerId());
		Customer fetchedCustomer2= customerService.viewCustomer(customer2.getCustomerId());
		
		System.out.println("\n********************* Updated Customers in database *********************\n");
		
		customerService.removeCustomer(fetchedCustomer1);
		
		System.out.println("\n********************* deleted a customer *********************\n");
		
		//List<Customer> listOfCustomers = customerService.viewCustomer(customer1);
//		
//		for(Customer customer:listOfCustomers) {
//			display(customer);
//		}
	}
public void display(Customer customer) {
	System.out.println("CustomerId: "+customer.getCustomerId()+"\ncustomer name: "+customer.getName()
	+"\nCustomer Email: "+customer.getEmailId()+"\nCustomer Mobile Number: "+customer.getMobileNumber()
	+"\nCustomer Password: "+customer.getPassword()+ "\nCustomer AddressId: "+ customer.getAddress().getAddressId()+"\nCustomer Flat no: "+ customer.getAddress().getFlatNo()
	+"\nCustomer Building Name: "+ customer.getAddress().getBuildingName()+
	//+"\n Cystomer area:"+ customer.getAddress().getArea()+
	"\nCustomer City: "+customer.getAddress().getCity()+
	"\nCustomer State: "+ customer.getAddress().getState()+
	"\nCustomer Pincode: "+ customer.getAddress().getPincode());
}
}











