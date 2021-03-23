package com.cg.vegetable.mgmt.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.vegetable.mgmt.entities.BillingDetails;

public class BillingServiceImpl implements IBillingService{
	
//	@Autowired
//	EntityManager entityManager;

	@Override
	public BillingDetails addBill(BillingDetails bill) {
//		entityManager.persist(bill);
		return null;
	}

	@Override
	public BillingDetails updateBill(BillingDetails bill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillingDetails viewBill(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
