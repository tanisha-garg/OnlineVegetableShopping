package com.cg.vegetable.mgmt.service;

import java.time.LocalDateTime;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.constants.TransactionMode;
import com.cg.vegetable.mgmt.constants.TransactionStatus;
import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.IBillingRepository;

@Service
public class BillingServiceImpl implements IBillingService{
	
	@Autowired
	private IBillingRepository billingRepository;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private ICustomerService customerService;

	
	/*
	 * 
	 * Saves Bill in the database after validation
	 * 
	 * @param bill is BillingDetails
	 * @return saved BillingDetails
	 * 
	 * */

	@Override
	public BillingDetails addBill(BillingDetails bill) {
		validateBill(bill);
		Order order = orderService.viewOrder(bill.getOrderId());
		Customer customer = customerService.viewCustomer(order.getCustomerId());
		bill.setTransactionStatus(TransactionStatus.PENDING);
		bill.setTransactionMode(TransactionMode.CASH_ON_DELIVERY);
		validateBillTransactions(bill);
		bill.setAddress(customer.getAddress());
		bill.setTransactionDate(currentDateTime());
		return billingRepository.save(bill);

	}
	
	/*
	 * 
	 * Updates Bill in database after validation
	 * 
	 *  @param bill is BillingDetails
	 *  @return updated BillingDetails
	 *  
	 * */

	@Override
	public BillingDetails updateBill(BillingDetails bill) {
		validateBill(bill);
		int id = bill.getBillingId();
		boolean exists = billingRepository.existsById(id);
		if(!exists) {
			throw new BillNotFoundException("Bill with id "+id+" doesn't exist");
		}
		return billingRepository.save(bill);

	}
	
	/*
	 * Find a bill from the database based on Id
	 * 
	 * @param id is billingId
	 * @return fetched BillingDetails
	 * 
	 * */

	@Override
	public BillingDetails viewBill(int id) {
		Optional<BillingDetails> billOptional = billingRepository.findById(id);
		if(!billOptional.isPresent()) {
			throw new BillNotFoundException("Bill with id "+id+" doesn't exist");
		}
		return billOptional.get();
	
	}
	
	/*
	 * 
	 * Generates the time when bill is created
	 * 
	 * @return LocalDateTime.now() is current time 
	 * 
	 * */
	
	
	public LocalDateTime currentDateTime() {
		return LocalDateTime.now();
	}
	
	/*
	 * 
	 * Validates a bill 
	 * 
	 * @param bill is BillingDetails
	 * @return void
	 * 
	 * */
	
	public void validateBill(BillingDetails bill) {		
		if(bill == null) {
			throw new InvalidBillException("Bill is Invalid");
		}
	}
	
	/*
	 * 
	 * Validates the transaction mode and transaction status in a bill 
	 * 
	 * @param bill is BillingDetails
	 * @return void
	 * 
	 * */
	
	public void validateBillTransactions(BillingDetails bill) {		
		
		validateMode(bill.getTransactionMode());
		validateStatus(bill.getTransactionStatus());
	}
	
	
	/*
	 * 
	 * Validates Transaction Mode in BillingDetails
	 * 
	 * @param transactionMode is data member of BillingDetails
	 * @return void
	 * 
	 * */
	
	public void validateMode(String transactionMode) {
		if(transactionMode == null || transactionMode.trim().isEmpty()) {
			throw new InvalidTransactionModeException("Transaction Mode cannot be empty or null");
		}
		
	}
	
	/*
	 * 
	 * Validates TransactionStatus in BillingDetails
	 * 
	 * @param transactionStatus is data member of BillingDetails
	 * @return void 
	 * 
	 * */
	
	public void validateStatus(String transactionStatus) {
		if(transactionStatus == null || transactionStatus.trim().isEmpty()) {
			throw new InvalidTransactionStatusException("Transaction Status cannot be empty or null");
		}
	}
	


}
