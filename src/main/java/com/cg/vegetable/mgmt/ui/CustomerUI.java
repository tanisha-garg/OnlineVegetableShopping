package com.cg.vegetable.mgmt.ui;

import java.util.List;

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
		
		address1.setFlatNo("206");
		address1.setBuildingName("Vimal Enclave");
		address1.setArea("Ashok vihar");
		address1.setCity("Mumbai");
		address1.setState("Maharashtra");
		address1.setPincode("6998086\n\n");
		
		address2.setFlatNo("407");
		address2.setBuildingName("Anand Heights");
		address2.setArea("Ashok nagar");
		address2.setCity("Panji");
		address2.setState("Goa");
		address2.setPincode("69980342\n\n");
		
		address3.setFlatNo("B-701");
		address3.setBuildingName("Vyankatesh Apartments");
		address3.setArea("Ashok van");
		address3.setCity("Chennai");
		address3.setState("Tamil Nadu");
		address3.setPincode("69982412\n\n"); 
		
		
		
		System.out.println("   \n  Adding Customer     ");
		
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		Customer customer3 = new Customer();
		
		
		customer1.setName("Ayesha ");
		customer1.setEmailid("aghikja@gmail.com");
		customer1.setMobileNumber("9876543219");
		customer1.setPassword("ayesha_15*43");
		customer1.setAddress(address1);
		
		
	
		customer2.setName("Shivangi");
		customer2.setEmailid("shivangi_087@gmail.com");
		customer2.setMobileNumber("7896543120");
		customer2.setPassword("abc@102");
		customer2.setAddress(address2);
		
	
		customer3.setName("saurabh");
		customer3.setEmailid("saurabh87@gmail.com");
		customer3.setMobileNumber("8764321984");
		customer3.setPassword("saurabh@10*3");
		customer3.setAddress(address3);
		
		customerService.addCustomer(customer1);
		customerService.addCustomer(customer2);
		customerService.addCustomer(customer3);
		
		System.out.println("\n********************* Customers saved  in database *********************\n");
		display(customer1);
		display(customer2);
		display(customer3);
		
		customer1.setPassword("newpswdCreated");
		customer2.setName("dogs");
		
		
		customerService.updateCustomer(customer1);
		customerService.updateCustomer(customer2);
		
		
		System.out.println("\n********************* Updated Customer Details in database *********************\n");
		
		Customer fetchedCustomer1= customerService.viewCustomer(customer1.getCustomerId());
		display(fetchedCustomer1);
		
		Customer fetchedCustomer2= customerService.viewCustomer(customer2.getCustomerId());
		display(fetchedCustomer2);
		
		
		
		customerService.removeCustomer(fetchedCustomer1);
		
		System.out.println("\n********************* deleted a customer *********************\n");
		
	//	List<Customer> listOfCustomers = customerService.viewCustomerList(location);
		
//		for(Customer customer:listOfCustomers) {
//			display(customer);
//		}
	}
public void display(Customer customer) {
	System.out.println("CustomerId: "+customer.getCustomerId()+"\ncustomer name: "+customer.getName()
	+"\nCustomer Email: "+customer.getEmailId()+"\nCustomer Mobile Number: "+customer.getMobileNumber()
	+"\nCustomer Password: "+customer.getPassword()+ "\nCustomer AddressId: "+ customer.getAddress().getAddressId()+"\nCustomer Flat no: "+ customer.getAddress().getFlatNo()
	+"\nCustomer Building Name: "+ customer.getAddress().getBuildingName()+
	"\nCustomer area: "+ customer.getAddress().getArea()+
	"\nCustomer City: "+customer.getAddress().getCity()+
	"\nCustomer State: "+ customer.getAddress().getState()+
	"\nCustomer Pincode: "+ customer.getAddress().getPincode());
}
}











