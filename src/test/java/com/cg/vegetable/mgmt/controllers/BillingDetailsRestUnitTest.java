package com.cg.vegetable.mgmt.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.vegetable.mgmt.dto.AddBillDetailsRequest;
import com.cg.vegetable.mgmt.dto.BillingDetailsResponse;
import com.cg.vegetable.mgmt.dto.UpdateTransactionModeRequest;
import com.cg.vegetable.mgmt.dto.UpdateTransactionStatusRequest;
import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.util.BillingDetailsUtil;

@ExtendWith(MockitoExtension.class)
public class BillingDetailsRestUnitTest {
	
	@Mock
	IBillingService billingService;
	
	@Mock
	BillingDetailsUtil billUtil;
	
	@Spy
	@InjectMocks
	BillingRestController billController;
	
	/*
	 * Scenario: Bill Details is fetched from BillingDetails and a BillingDetailsResponse is sent successfully
	 * Input: billId as path variable
	 * Expectation: BillingDetailsResponse sent to UI
	 * 
	 * */
	
	@Test
	public void testFetchBill_1() {
		int billId = 1;
		BillingDetails bill = mock(BillingDetails.class);
		BillingDetailsResponse response = mock(BillingDetailsResponse.class);
		when(billingService.viewBill(billId)).thenReturn(bill);
		when(billUtil.toDetails(bill)).thenReturn(response);
		BillingDetailsResponse result = billController.fetchBillDetails(billId);
		assertEquals(response, result);
		verify(billingService).viewBill(billId);
		verify(billUtil).toDetails(bill);
	}
	
	/*
	 * Scenario: Update Transaction Status in database and send a BillingDetailsResponse
	 * Input: billId as path variable and transaction status from request body
	 * Expectation: Updated BillingDetailsResponse sent to UI
	 * 
	 * */
	
	@Test
	public void testUpdateTransactionStatus_1() {
		int billId = 1;
		String transactionStatus = "Successful";
		BillingDetails bill = mock(BillingDetails.class);
		BillingDetailsResponse response = mock(BillingDetailsResponse.class);
		UpdateTransactionStatusRequest request = mock(UpdateTransactionStatusRequest.class);
		when(billingService.viewBill(billId)).thenReturn(bill);
		when(request.getTransactionStatus()).thenReturn(transactionStatus);
		when(billingService.updateBill(bill)).thenReturn(bill);
		when(billUtil.toDetails(bill)).thenReturn(response);
		BillingDetailsResponse result = billController.updateTransactionStatus(request, billId);
		assertEquals(response, result);
		verify(billingService).viewBill(billId);
		verify(billingService).updateBill(bill);
		verify(billUtil).toDetails(bill);
		

	}
	
	/*
	 * Scenario: Update Transaction Mode in database and send a BillingDetailsResponse
	 * Input: billId as path variable and transaction mode as request body
	 * Expectation: Updated BillingDetailsResponse sent to UI
	 * 
	 * */
	
	@Test
	public void testUpdateTransactionMode_1() {
		int billId = 1;
		String transactionMode = "Debit Card";
		BillingDetails bill = mock(BillingDetails.class);
		BillingDetailsResponse response = mock(BillingDetailsResponse.class);
		UpdateTransactionModeRequest request = mock(UpdateTransactionModeRequest.class);
		when(billingService.viewBill(billId)).thenReturn(bill);
		when(request.getTransactionMode()).thenReturn(transactionMode);
		when(billingService.updateBill(bill)).thenReturn(bill);
		when(billUtil.toDetails(bill)).thenReturn(response);
		BillingDetailsResponse result = billController.updateTransactionMode(request, billId);
		assertEquals(response, result);
		verify(billingService).viewBill(billId);
		verify(billingService).updateBill(bill);
		verify(billUtil).toDetails(bill);
		

	}
	
	/*
	 * Scenario: Add Bill Successfully
	 * Input: Request body with data from UI
	 * Expectation: BillingDetailsResponse of added bill sent to UI
	 * 
	 * */
	
	@Test
	public void testAddBill_1() {
		int orderId = 1;
		String transactionMode = "Credit card";
		String transactionStatus = "Successful";
		AddBillDetailsRequest request = mock(AddBillDetailsRequest.class);
		BillingDetailsResponse response = mock(BillingDetailsResponse.class);
		when(request.getOrderId()).thenReturn(orderId);
		when(request.getTransactionMode()).thenReturn(transactionMode);
		when(request.getTransactionStatus()).thenReturn(transactionStatus);
		BillingDetails bill = new BillingDetails(orderId, transactionMode, transactionStatus);
		when(billingService.addBill(bill)).thenReturn(bill);
		when(billUtil.toDetails(bill)).thenReturn(response);
		BillingDetailsResponse result = billController.addBill(request);
		assertEquals(response, result);
		verify(billingService).addBill(bill);
		verify(billUtil).toDetails(bill);
	}

}
