package com.cg.vegetable.mgmt.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.cg.vegetable.mgmt.constants.VegetableCategory;
import com.cg.vegetable.mgmt.exceptions.InvalidCategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.cg.vegetable.mgmt.dto.AddVegetableRequest;
import com.cg.vegetable.mgmt.dto.DeleteVegetableRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableCategoryRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableNameRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetablePriceRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableQuantityRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableTypeRequest;
import com.cg.vegetable.mgmt.dto.VegetableDetails;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;
import com.cg.vegetable.mgmt.util.VegetableUtil;

@RequestMapping("/vegetables")
@RestController
public class VegetableRestController {
	
	@Autowired
	private IVegetableMgmtService vegService;
	
	@Autowired
	private VegetableUtil vegUtil;
	

	/*
	 * scenario : success scenario , vegetable is added in database through postman
	 *
	 * input : Details of vegetable is required by user
	 *
	 *  expectation : vegetable is added , and displayed in postman
	 */
	 @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping("/add")
	    public VegetableDetails addVegetable(@RequestBody AddVegetableRequest requestData) {

	        Vegetable created = new Vegetable(requestData.getName(),requestData.getType(),requestData.getCategory(),requestData.getPrice(),requestData.getQuantity());
	        Vegetable added=vegService.addVegetable(created);
	        return vegUtil.toDetails(added);
	    }



	 /*
		 * scenario : success scenario , vegetable is fetched from database through postman
		 *
		 * input : Id of vegetable is required by user
		 *
		 *  expectation : vegetable is fetched , and displayed in postman
		 */
	 	
	 @GetMapping(value = "/byid/{id}")
	    public VegetableDetails fetchVegetable(@PathVariable("id") Integer vegId) {
		 Vegetable vegetable = vegService.viewVegetable(vegId);
		 VegetableDetails details=vegUtil.toDetails(vegetable);
	        return details;
	    }
	 
	 /*
		 * scenario : success scenario , vegetable is deleted in database through postman
		 *
		 * input : Details of vegetable is required by user which is to be deleted
		 *
		 *  expectation : vegetable is deleted , and deleted vegetable is displayed in postman
		 */
	 @DeleteMapping("/delete")
	    public String delete(@RequestBody   DeleteVegetableRequest requestData){
	        Vegetable vegetable=vegService.viewVegetable(requestData.getVegId());
	        Vegetable removed = vegService.removeVegetable(vegetable);
	        return "vegetable deleted for id="+requestData.getVegId();
	    }
	 
	    /*
		 * scenario : success scenario , vegetable name is changed in database through postman
		 *
		 * input : Id and name of vegetable is required by user
		 *
		 *  expectation : vegetable name is changed , and displayed in postman
		 */
	 
	 @PutMapping("/changename")
	    public VegetableDetails changeName(@RequestBody  UpdateVegetableNameRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setName(requestData.getName());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(updatedVegetable);
	        return desired;
	    }
	 
	        /*
			 * scenario : success scenario , vegetable category is changed in database through postman
			 *
			 * input : Id and category of vegetable is required by user
			 *
			 *  expectation : vegetable category is changed , and displayed in postman
			 */
	 
	 @PutMapping("/changeCategory")
	    public VegetableDetails changeCategory(@RequestBody  UpdateVegetableCategoryRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setCategory(requestData.getCategory());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 return vegUtil.toDetails(updatedVegetable);
	    }
	 
	 	/*
		 * scenario : success scenario , vegetable type is changed in database through postman
		 *
		 * input : Id and type of vegetable is required by user
		 *
		 *  expectation : vegetable type is changed , and displayed in postman
		 */
	 
	 @PutMapping("/changeType")
	    public VegetableDetails changeType(@RequestBody  UpdateVegetableTypeRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setType(requestData.getType());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(updatedVegetable);
	        return desired;
	    }
	 
	 	/*
		 * scenario : success scenario , vegetable quantity is changed in database through postman
		 *
		 * input : Id and quantity of vegetable is required by user
		 *
		 *  expectation : vegetable quantity is changed , and displayed in postman
		 */
	 @PutMapping("/changeQuantity")
	    public VegetableDetails changeQuantity(@RequestBody  UpdateVegetableQuantityRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setQuantity(requestData.getQuantity());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(updatedVegetable);
	        return desired;
	    }
	 
	 	/*
		 * scenario : success scenario , vegetable price is changed in database through postman
		 *
		 * input : Id and price of vegetable is required by user
		 *
		 *  expectation : vegetable price is changed , and displayed in postman
		 */
	 @PutMapping("/changePrice")
	    public VegetableDetails changePrice(@RequestBody UpdateVegetablePriceRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setPrice(requestData.getPrice());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(updatedVegetable);
	        return desired;
	    }
	 
	 @GetMapping("/viewAllByName/{name}")
	    public List<VegetableDetails>findVegetablesByName(@PathVariable("name") String name){
	      List<Vegetable>vegList=  vegService.viewVegetableByName(name);
	      List<VegetableDetails>desired=vegUtil.toDetailsList(vegList);
	      return desired;
	    }
	 
	 @GetMapping("/viewAllByCategory/{category}")
	    public List<VegetableDetails>findVegetablesByCategory(@PathVariable("category") String category){
	      List<Vegetable>vegList=  vegService.viewVegetableByCategory(category);
	      List<VegetableDetails>desired=vegUtil.toDetailsList(vegList);
	      return desired;
	    }
	 @GetMapping("/viewAll")
	    public List<VegetableDetails>findAllVegetables(){
	      List<Vegetable>vegList=  vegService.viewAllVegetables();
	      List<VegetableDetails>desired=vegUtil.toDetailsList(vegList);
	      return desired;
	    }
	 
}
