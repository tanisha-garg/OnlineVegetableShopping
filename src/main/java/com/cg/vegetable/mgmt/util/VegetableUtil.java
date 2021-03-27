package com.cg.vegetable.mgmt.util;

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

}
