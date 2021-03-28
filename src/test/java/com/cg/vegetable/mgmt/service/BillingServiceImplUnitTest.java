package com.cg.vegetable.mgmt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.exceptions.BillNotFoundException;
import com.cg.vegetable.mgmt.exceptions.InvalidTransactionModeException;
import com.cg.vegetable.mgmt.exceptions.InvalidTransactionStatusException;
import com.cg.vegetable.mgmt.repository.IBillingRepository;


//@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BillingServiceImplUnitTest {
	
	@Mock
	IBillingRepository billingRepository;

	@Spy
	@InjectMocks
	BillingServiceImpl billingService;
	
	
	/*
	 * Scenario: Add Bill successfully
	 * Test Case: Add Bill
	 */	
	@Test
	public void testAddBill_1() {
		BillingDetails saved = Mockito.mock(BillingDetails.class);
		BillingDetails billDetails = Mockito.mock(BillingDetails.class);
		doNothing().when(billingService).validateBill(billDetails);
		LocalDateTime now = LocalDateTime.now();
		doReturn(now).when(billingService).currentDateTime();
		when(billingRepository.save(billDetails)).thenReturn(saved);
		BillingDetails result = billingService.addBill(billDetails);
		assertNotNull(result);
		assertEquals(saved, result);
		verify(billingRepository).save(billDetails);
		verify(billingService).validateBill(billDetails);
	}
	
	/*
	 * Scenario: TransactionMode Validation - Blank input
	 * Test Case: Add Bill
	 */	
	
	@Test
	public void testAddBill_2() {
//		String transactionMode = "";
		BillingDetails bill = Mockito.mock(BillingDetails.class);
		doThrow(InvalidTransactionModeException.class).when(billingService).validateBill(bill);
		Executable executable = () -> billingService.addBill(bill);
		assertThrows(InvalidTransactionModeException.class, executable);
		verify(billingRepository, never()).save(bill);
		
	}
	
	/*
	 * Scenario: TransactionMode Validation - Null input
	 * Test Case: Add Bill
	 */	
	@Test
	public void testAddBill_3() {
//		String transactionMode = null;
		BillingDetails bill = Mockito.mock(BillingDetails.class);
		doThrow(InvalidTransactionModeException.class).when(billingService).validateBill(bill);
		Executable executable = () -> billingService.addBill(bill);
		assertThrows(InvalidTransactionModeException.class, executable);
		verify(billingRepository, never()).save(bill);
	}
	
	/*
	 * Scenario: TransactionStatus Validation - Blank input
	 * Test Case: Add bill
	 */	
	@Test
	public void testAddBill_4() {
//		String transactionStatus = "";
		BillingDetails bill = Mockito.mock(BillingDetails.class);
		doThrow(InvalidTransactionStatusException.class).when(billingService).validateBill(bill);
		Executable executable = () -> billingService.addBill(bill);
		assertThrows(InvalidTransactionStatusException.class, executable);
		verify(billingRepository, never()).save(bill);
	}
	
	/*
	 * Scenario: TransactionStatus Validation - Null input
	 * Test Case: Add bill
	 */	
	@Test
	public void testAddBill_5() {
//		String transactionStatus = null;
		BillingDetails bill = Mockito.mock(BillingDetails.class);
		doThrow(InvalidTransactionStatusException.class).when(billingService).validateBill(bill);
		Executable executable = () -> billingService.addBill(bill);
		assertThrows(InvalidTransactionStatusException.class, executable);
		verify(billingRepository, never()).save(bill);
	}
	
	/*
	 * Scenario: To view bill of a given billing id - Success
	 * Test Case: View Bill
	 */	
	@Test
	public void viewBill_1() {
		int billingId = 1;
		BillingDetails bill = Mockito.mock(BillingDetails.class);
		Optional<BillingDetails> optional = Optional.of(bill);
		when(billingRepository.findById(billingId)).thenReturn(optional);
		BillingDetails result = billingService.viewBill(billingId);
		assertEquals(bill, result);
		//verify(billingRepository).findById(billingId);
	}
	
	/*
	 * Scenario: To view bill of a given billing id - BillNotFoundException
	 * Test Case: View Bill
	 */	
	@Test
	public void viewBill_2() {
		int billingId = 11;
		Optional<BillingDetails> optional = Optional.empty();
		when(billingRepository.findById(billingId)).thenReturn(optional);
		Executable executable = () -> billingService.viewBill(10);
		assertThrows(BillNotFoundException.class, executable);
	}
	
	/*
	 * Scenario: To update bill - success
	 * Test Case: Update Bill
	 */	
	@Test
	public void updateBill_1() {
		int billingId = 1;
		BillingDetails bill = Mockito.mock(BillingDetails.class);
		BillingDetails saved = Mockito.mock(BillingDetails.class);
		doNothing().when(billingService).validateBill(bill);
		when(bill.getBillingId()).thenReturn(billingId);
		when(billingRepository.existsById(billingId)).thenReturn(true);
		when(billingRepository.save(bill)).thenReturn(saved);
		BillingDetails result = billingService.updateBill(bill);
		assertNotNull(result);
		assertEquals(saved, result);
		verify(billingRepository).save(bill);		
		verify(billingService).validateBill(bill);
		
	}
	
	/*
	 * Scenario: To update bill - Bill Not Found Exception
	 * Test Case: Update Bill
	 */	
	@Test
	public void updateBill_2() {
		int billingId = 10;
		BillingDetails bill = Mockito.mock(BillingDetails.class);
		doNothing().when(billingService).validateBill(bill);
		when(bill.getBillingId()).thenReturn(billingId);
		when(billingRepository.existsById(billingId)).thenReturn(false);
		Executable executable = () -> billingService.updateBill(bill);
		assertThrows(BillNotFoundException.class, executable);
		verify(billingRepository, never()).save(bill);
	}
	

	
}
