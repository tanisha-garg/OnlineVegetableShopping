package com.cg.vegetable.mgmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.IBillingRepository;


@ExtendWith(SpringExtension.class)
public class BillingServiceImplUnitTest {
	
	@Mock
	IBillingRepository billingRepository;

	@Spy
	@InjectMocks
	BillingServiceImpl billingService;
	
	
	/*
	 * 
	 * Scenario: Bill is saved successfully
	 * Input: Mock Bill object, stubbing validate method
	 * Expectation: Verifying IBillingRepository#save(bill) is called
	 * 
	 */	
	
	@Test
	public void testAddBill_1() {
		BillingDetails saved = mock(BillingDetails.class);
		BillingDetails billDetails = mock(BillingDetails.class);
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
	 * 
	 * Scenario: Validation Fails
	 * Input: Mock Bill object, stubbing validation method so that it throws exception
	 * Expectation: Throws InvalidBillException
	 * 
	 */	
	
	@Test
	public void testAddBill_2() {
		BillingDetails bill = mock(BillingDetails.class);
		doThrow(InvalidBillException.class).when(billingService).validateBill(bill);
		Executable executable = () -> billingService.addBill(bill);
		assertThrows(InvalidBillException.class, executable);
		verify(billingRepository, never()).save(bill);
		
	}

	
	
	/*
	 * 
	 * Scenario: Bill is fetched successfully
	 * Expectation: Verifying IBillingRepository#findById(billingId) is called 
	 *  
	 */	
	@Test
	public void viewBill_1() {
		int billingId = 1;
		BillingDetails bill = mock(BillingDetails.class);
		Optional<BillingDetails> optionalBill = Optional.of(bill);
		when(billingRepository.findById(billingId)).thenReturn(optionalBill);
		BillingDetails result = billingService.viewBill(billingId);
		assertEquals(bill, result);
		verify(billingRepository).findById(billingId);
	}
	
	/*
	 * Scenario: Fetching Fails
	 * Expectation: Throws BillNotFoundException
	 * 
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
	 * Scenario: BillingDetails are updated successfully
	 * Input: Mock BillingDetails Object, stubbing validate method
	 * Expectation: Verifying IBillingRepository#save(bill) is called
	 */	
	@Test
	public void updateBill_1() {
		int billingId = 1;
		BillingDetails bill = mock(BillingDetails.class);
		BillingDetails saved = mock(BillingDetails.class);
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
	 * Scenario: Validation while updating BillingDetails fails
	 * Input: Mock BillingDetails Object, stub validate method
	 * Expectation: Throws InvalidBillException and verifying IBillingRepository#save is never called
	 * 
	 */	
	@Test
	public void updateBill_2() {
		BillingDetails bill = mock(BillingDetails.class);
		doThrow(InvalidBillException.class).when(billingService).validateBill(bill);
		Executable executable = () -> billingService.addBill(bill);
		assertThrows(InvalidBillException.class, executable);
		verify(billingRepository, never()).save(bill);
	}
	
	/*
	 * Scenario: Updating Billing Details fails
	 * Input: Mock BillingDetails Object, stub validate method
	 * Expectation: Throws BillNotFoundException and verifying IBillingRepository#save is never called
	 * 
	 */	
	@Test
	public void updateBill_3() {
		int billingId = 10;
		BillingDetails bill = mock(BillingDetails.class);
		doNothing().when(billingService).validateBill(bill);
		when(bill.getBillingId()).thenReturn(billingId);
		when(billingRepository.existsById(billingId)).thenReturn(false);
		Executable executable = () -> billingService.updateBill(bill);
		assertThrows(BillNotFoundException.class, executable);
		verify(billingRepository, never()).save(bill);
	}
	
	/*
	 * 
	 * Scenario: BillingDetails object passed is null
	 * Input: BillingDetails null object 
	 * Expectation: Throws InvalidBillException  
	 * */
	
	@Test
	public void validateBill_1() {
		BillingDetails bill = null;
		Executable executable = () -> billingService.validateBill(bill);
		assertThrows(InvalidBillException.class, executable);		
	}
	
	/*
	 * 
	 * Scenario: When transaction mode passed for validation is null
	 * Input: transactionMode is null
	 * Expectation: Throws InvalidTransactionModeException
	 * 
	 * */
	
	@Test
	public void validateMode_1() {
		String transactionMode = null;
		Executable executable = () -> billingService.validateMode(transactionMode);
		assertThrows(InvalidTransactionModeException.class, executable);
	}
	
	/*
	 * 
	 * Scenario: When transaction mode passed for validation is empty
	 * Input: transactionMode is empty
	 * Expectation: Throws InvalidTransactionModeException
	 * 
	 * */
	
	@Test
	public void validateMode_2() {
		String transactionMode = "";
		Executable executable = () -> billingService.validateMode(transactionMode);
		assertThrows(InvalidTransactionModeException.class, executable);
	}
	
	/*
	 * 
	 * Scenario: When transaction status passed for validation is null
	 * Input: transactionStatus is null
	 * Expectation: Throws InvalidTransactionStatusException
	 * 
	 * */
	
	@Test
	public void validateStatus_1() {
		String transactionStatus = null;
		Executable executable = () -> billingService.validateMode(transactionStatus);
		assertThrows(InvalidTransactionModeException.class, executable);
	}
	
	/*
	 * 
	 * Scenario: When transaction status passed for validation is empty
	 * Input: transactionStatus is empty
	 * Expectation: Throws InvalidTransactionStatusException
	 * 
	 * */
	
	@Test
	public void validateStatus_2() {
		String transactionStatus = "";
		Executable executable = () -> billingService.validateMode(transactionStatus);
		assertThrows(InvalidTransactionModeException.class, executable);
	}

	
}
