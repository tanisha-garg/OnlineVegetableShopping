package com.cg.vegetable.mgmt.util;

import java.util.*;

import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.dto.CartDetails;
import com.cg.vegetable.mgmt.dto.VegetableDetails;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.CartVegetable;
import com.cg.vegetable.mgmt.entities.Vegetable;

@Component
public class CartUtil {
	
	public CartDetails toDetails(CartVegetable cartVeg){
		CartDetails details=new CartDetails();
        details.setCartId(cartVeg.getCart().getCartId());
        details.setCustId(cartVeg.getCart().getCustId());
        List<VegetableDetails>allVeg=new ArrayList<>();
        
       // details.setVegetables(cartVeg.getVegetable());
        return details;
    }

}
