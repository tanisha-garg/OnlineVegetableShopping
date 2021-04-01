package com.cg.vegetable.mgmt.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cg.vegetable.mgmt.dto.BillingDetailsResponse;
import com.cg.vegetable.mgmt.dto.UpdateTransactionModeRequest;
import com.cg.vegetable.mgmt.dto.UpdateTransactionStatusRequest;
import com.cg.vegetable.mgmt.dto.AddBillDetailsRequest;
import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.util.BillingDetailsUtil;

@Validated
@RequestMapping("/bills")
@RestController
public class BillingRestController {

    @Autowired
    private IBillingService billingService;

    @Autowired
    private BillingDetailsUtil billUtil;

    @GetMapping(value = "get/{id}")
    public BillingDetailsResponse fetchBillDetails(@PathVariable("id") @Min(1) int id) {
        BillingDetails bill = billingService.viewBill(id);
        return billUtil.toDetails(bill);
    }

    @PutMapping("/update/status/{id}")
    public BillingDetailsResponse updateTransactionStatus(@RequestBody @Valid UpdateTransactionStatusRequest requestData,
    												@PathVariable("id") @Min(1) int id) {
        BillingDetails bill = billingService.viewBill(id);
        bill.setTransactionStatus(requestData.getTransactionStatus());
        BillingDetails updatedBill = billingService.updateBill(bill);
        return billUtil.toDetails(updatedBill);
    }
    
    @PutMapping("/update/mode/{id}")
    public BillingDetailsResponse updateTransactionMode(@RequestBody @Valid UpdateTransactionModeRequest requestData,
    													@PathVariable("id") @Min(1) int id) {
    	BillingDetails bill = billingService.viewBill(id);
    	bill.setTransactionMode(requestData.getTransactionMode());
    	BillingDetails updatedBill = billingService.updateBill(bill);
    	return billUtil.toDetails(updatedBill);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public BillingDetailsResponse addBill(@RequestBody @Valid AddBillDetailsRequest requestData) {
        BillingDetails bill = new BillingDetails(requestData.getOrderId(), requestData.getTransactionMode(),
                requestData.getTransactionStatus());
        BillingDetails addedBill = billingService.addBill(bill);
        return billUtil.toDetails(addedBill);
    }

}
