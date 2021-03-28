package com.cg.vegetable.mgmt.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.IBillingRepository;

@Service
public class BillingServiceImpl implements IBillingService{
	
	@Autowired
	private IBillingRepository billingRepository;
	
	public LocalDateTime currentDateTime() {
		return LocalDateTime.now();
	}
	
	/*
	 * 
	 * Saves Bill in the database after validation
	 * 
	 * @param bill is BillingDetails
	 * @return saved BillingDetails
	 * 
	 * */

	@Transactional
	@Override
	public BillingDetails addBill(BillingDetails bill) {
		validateBill(bill);
		bill.setTransactionDate(currentDateTime());
		BillingDetails saved = billingRepository.save(bill);
		return saved;

	}
	
	/*
	 * 
	 * Updates Bill in database after validation
	 * 
	 *  @param bill is BillingDetails
	 *  @return updated BillingDetails
	 * */

	@Transactional
	@Override
	public BillingDetails updateBill(BillingDetails bill) {
		validateBill(bill);
		int id = bill.getBillingId();
		boolean exists = billingRepository.existsById(id);
		if(!exists) {
			throw new BillNotFoundException("Bill with id "+id+" doesn't exist");
		}
		BillingDetails saved = billingRepository.save(bill);
		return saved;

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
		BillingDetails bill = billOptional.get();
		return bill;
	
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
