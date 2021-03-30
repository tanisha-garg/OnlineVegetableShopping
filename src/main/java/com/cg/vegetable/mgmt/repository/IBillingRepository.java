package com.cg.vegetable.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vegetable.mgmt.entities.BillingDetails;

public interface IBillingRepository extends JpaRepository<BillingDetails, Integer>{
	
	BillingDetails findBillingDetailsByOrderId(int orderId);

}
