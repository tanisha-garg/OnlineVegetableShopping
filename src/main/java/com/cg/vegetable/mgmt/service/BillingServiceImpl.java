package com.cg.vegetable.mgmt.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.exceptions.BillNotFoundException;
import com.cg.vegetable.mgmt.repository.IBillingRepository;

@Service
public class BillingServiceImpl implements IBillingService{
	
	@Autowired
	IBillingRepository billingRepository;

	@Transactional
	@Override
	public BillingDetails addBill(BillingDetails bill) {
		billingRepository.save(bill);
		return bill;
	}

	@Transactional
	@Override
	public BillingDetails updateBill(BillingDetails bill) {
		bill = billingRepository.save(bill);
		return bill;
	}

	@Override
	public BillingDetails viewBill(int id) {
		Optional<BillingDetails> optional = billingRepository.findById(id);
		if(!optional.isPresent()) {
			throw new BillNotFoundException("Bill with id "+id+" doesn't exist");
		}
		BillingDetails bill = optional.get();
		return bill;
	}

}
