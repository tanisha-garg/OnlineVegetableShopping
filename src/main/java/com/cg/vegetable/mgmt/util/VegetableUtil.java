package com.cg.vegetable.mgmt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;


import com.cg.vegetable.mgmt.dto.VegetableDetails;
import com.cg.vegetable.mgmt.entities.Vegetable;
@Component
public class VegetableUtil {
	
	public VegetableDetails toDetails(Vegetable vegetable){
        VegetableDetails details=new VegetableDetails();
        details.setVegId(vegetable.getVegId());
        details.setName(vegetable.getName());
        details.setCategory(vegetable.getCategory());
        details.setType(vegetable.getType());
        details.setQuantity(vegetable.getQuantity());
        details.setPrice(vegetable.getPrice());
        return details;
    }
	
	 public List<VegetableDetails> toDetailsList(List<Vegetable> vegetables ){
	        List<VegetableDetails>desired=new ArrayList<>();
	        for (Vegetable vegetable:vegetables){
	        	VegetableDetails details=toDetails(vegetable);
	            desired.add(details);
	        }
	        return desired;

	    }

}
