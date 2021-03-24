package com.cg.vegetable.mgmt.service;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.IBillingRepository;

@ExtendWith(MockitoExtension.class)
public class BillingServiceImplUnitTest {
	
	@Mock
	IBillingRepository repository;
	
	@Spy
	@InjectMocks
	BillingServiceImpl billingService;
	
	
	/*
	 * Scenario: Add Bill successfully
	 * Test Case: Add Bill
	 */	
	@Test
	public void testAddBill_1() {
		int billingId = 1;
		int orderId = 2;
		String transactionMode = "UPI";
		String transactionDate = "23/3/2021";
		String transactionStatus = "Success";
		BillingDetails saved = Mockito.mock(BillingDetails.class);
		BillingDetails billDetails = Mockito.mock(BillingDetails.class);
		when(repository.save(billDetails)).thenReturn(saved);
		BillingDetails bill = new BillingDetails(billingId, orderId, transactionMode, transactionDate, transactionStatus);
		BillingDetails result = billingService.addBill(bill);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(saved, result);
//		Assertions.assertEquals(billingId, result.getBillingId());
//		Assertions.assertEquals(orderId, result.getOrderId());
//		Assertions.assertEquals(transactionMode, result.getTransactionMode());
//		Assertions.assertEquals(transactionDate, result.getTransactionDate());
//		Assertions.assertEquals(transactionStatus, result.getTransactionStatus());
		
	}
	
	/*
	 * Scenario: TransactionMode Validation - Blank input
	 * Test Case: Add Bill
	 */	
	@Test
	public void testAddBill_2() {
		String transactionMode = "";
		BillingDetails bill = new BillingDetails(1, 2, transactionMode, "23/9/2020", "Failed");
		Executable executable = () -> billingService.addBill(bill);
		Assertions.assertThrows(InvalidTransactionModeException.class, executable);
		
	}
	
	/*
	 * Scenario: TransactionMode Validation - Null input
	 * Test Case: Add Bill
	 */	
	@Test
	public void testAddBill_3() {
		String transactionMode = null;
		BillingDetails bill = new BillingDetails(1, 2, transactionMode, "23/9/2020", "Failed");
		Executable executable = () -> billingService.addBill(bill);
		Assertions.assertThrows(InvalidTransactionModeException.class, executable);
		
	}
	
	/*
	 * Scenario: To view bill of a given billing id - Success
	 * Test Case: View Bill
	 */	
	@Test
	public void viewBill_1() {
		int billingId = 1;
		BillingDetails result = billingService.viewBill(billingId);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(billingId, result.getBillingId());
		Assertions.assertEquals("2", result.getOrderId());
		Assertions.assertEquals("UPI", result.getTransactionMode());
		Assertions.assertEquals( "23/9/2020", result.getTransactionDate());
		Assertions.assertEquals("Failed", result.getTransactionStatus());
	}
	
	/*
	 * Scenario: To view bill of a given billing id - BillNotFoundException
	 * Test Case: View Bill
	 */	
	@Test
	public void viewBill_2() {
		Executable executable = () -> billingService.viewBill(10);
		Assertions.assertThrows(BillNotFoundException.class, executable);
	}
	
	/*
	 * Scenario: To update bill - success
	 * Test Case: Update Bill
	 */	
	@Test
	public void updateBill_1() {
		
	}
	
}
