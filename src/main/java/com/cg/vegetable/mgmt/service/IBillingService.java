package com.cg.vegetable.mgmt.service;

import com.cg.vegetable.mgmt.entities.BillingDetails;

public interface IBillingService {
	
	public BillingDetails addBill(BillingDetails bill);
	public BillingDetails updateBill(BillingDetails bill);
	public BillingDetails viewBill(int id);

}
