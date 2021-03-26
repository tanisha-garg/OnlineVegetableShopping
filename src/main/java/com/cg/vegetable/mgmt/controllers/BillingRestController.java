package com.cg.vegetable.mgmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.vegetable.mgmt.dto.BillingDetailsDTO;
import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.util.BillingDetailsUtil;

@RequestMapping("/bills")
@RestController
public class BillingRestController {
	
	@Autowired
	private IBillingService billingService;
	
	@Autowired
	private BillingDetailsUtil billUtil;
	
	@GetMapping(value="/byId/{id}")
	public BillingDetailsDTO fetchBillDetails(@PathVariable("id") int id) {
		BillingDetails bill = billingService.viewBill(id);
		BillingDetailsDTO details = billUtil.toDetails(bill);
		return details;
	}
	
	

}
