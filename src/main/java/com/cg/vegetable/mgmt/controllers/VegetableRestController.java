package com.cg.vegetable.mgmt.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;
import com.cg.vegetable.mgmt.util.VegetableUtil;

@RequestMapping("/vegetables")
@RestController
public class VegetableRestController {
	
	@Autowired
	private IVegetableMgmtService vegService;
	
	@Autowired
	private VegetableUtil vegUtil;
	
	@Autowired
	private IVegetableMgmtRepository vegRepository;
	
	 @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping("/add")
	    public String addVegetable(@RequestBody AddVegetableRequest requestData) {
	        Vegetable created = new Vegetable(requestData.getName(),requestData.getCategory(),requestData.getType(),requestData.getPrice(),requestData.getQuantity());
	        Vegetable added=vegService.addVegetable(created);
	        return "created vegetable with id=" + created.getVegId();
	    }
	 	
	 @GetMapping(value = "/byid/{id}")
	    public VegetableDetails fetchVegetable(@PathVariable("id") Integer vegId) {
		 Vegetable vegetable = vegService.viewVegetable(vegId);
		 VegetableDetails details=vegUtil.toDetails(vegetable);
	        return details;
	    }
	 @DeleteMapping("/delete")
	    public String delete(@RequestBody DeleteVegetableRequest requestData){
	        Optional<Vegetable>vegOptional=vegRepository.findById(requestData.getVegId());
	        Vegetable removed = vegService.removeVegetable(vegOptional.get());
	        return "vegetable deleted for id="+requestData.getVegId();
	    }
	 
	 @PutMapping("/changename")
	    public VegetableDetails changeName(@RequestBody UpdateVegetableNameRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setName(requestData.getName());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(vegetable);
	        return desired;
	    }
	 
	 @PutMapping("/changeCategory")
	    public VegetableDetails changeCategory(@RequestBody UpdateVegetableCategoryRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setCategory(requestData.getCategory());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(vegetable);
	        return desired;
	    }
	 
	 @PutMapping("/changeType")
	    public VegetableDetails changeType(@RequestBody UpdateVegetableTypeRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setType(requestData.getType());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(vegetable);
	        return desired;
	    }
	 @PutMapping("/changeQuantity")
	    public VegetableDetails changeQuantity(@RequestBody UpdateVegetableQuantityRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setQuantity(requestData.getQuantity());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(vegetable);
	        return desired;
	    }
	 @PutMapping("/changePrice")
	    public VegetableDetails changeName(@RequestBody UpdateVegetablePriceRequest requestData) {
		 Vegetable vegetable = vegService.viewVegetable(requestData.getVegId());
		 vegetable.setPrice(requestData.getPrice());
		 Vegetable updatedVegetable = vegService.updateVegetable(vegetable);
		 VegetableDetails desired=vegUtil.toDetails(vegetable);
	        return desired;
	    }
	 
}
