package com.cg.vegetable.mgmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.vegetable.mgmt.dto.BillingDetailsDTO;
import com.cg.vegetable.mgmt.dto.UpdateTransactionStatusRequest;
import com.cg.vegetable.mgmt.dto.AddBillDetailsRequest;
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

    @GetMapping(value = "get/{id}")
    public BillingDetailsDTO fetchBillDetails(@PathVariable("id") int id) {
        BillingDetails bill = billingService.viewBill(id);
        return billUtil.toDetails(bill);
    }

    @PutMapping("/updateStatus/{id}")
    public BillingDetailsDTO updateTransactionStatus(@RequestBody UpdateTransactionStatusRequest requestData,
    												@PathVariable("id") int id) {
        BillingDetails bill = billingService.viewBill(id);
        bill.setTransactionStatus(requestData.getTransactionStatus());
        BillingDetails updatedBill = billingService.updateBill(bill);
        return billUtil.toDetails(updatedBill);
    }

    @PostMapping("/add")
    public BillingDetailsDTO addBill(@RequestBody AddBillDetailsRequest requestData) {
        BillingDetails bill = new BillingDetails(requestData.getOrderId(), requestData.getTransactionMode(),
                requestData.getTransactionStatus());
        BillingDetails addedBill = billingService.addBill(bill);
        return billUtil.toDetails(addedBill);
    }

}
