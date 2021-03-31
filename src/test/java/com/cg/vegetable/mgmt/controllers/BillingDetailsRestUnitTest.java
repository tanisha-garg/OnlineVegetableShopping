package com.cg.vegetable.mgmt.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.util.BillingDetailsUtil;

@ExtendWith(MockitoExtension.class)
public class BillingDetailsRestUnitTest {
	
	@Mock
	IBillingService billingService;
	
	@Mock
	BillingDetailsUtil billingDetailsUtil;
	
	@Spy
	@InjectMocks
	BillingRestController billController;
	
	

}
