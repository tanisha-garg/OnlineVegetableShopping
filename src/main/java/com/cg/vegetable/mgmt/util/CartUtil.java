package com.cg.vegetable.mgmt.util;

import java.util.*;

import com.cg.vegetable.mgmt.dto.CartVegetableDetails;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.dto.CartDetails;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.CartVegetable;
import com.cg.vegetable.mgmt.entities.Vegetable;

@Component
public class CartUtil {

    public List<CartVegetableDetails> toCarVegetablesList(Collection<CartVegetable> vegetables) {
        List<CartVegetableDetails> desired = new ArrayList<>();
        if (vegetables == null) {
            return desired;
        }

        for (CartVegetable vegetable : vegetables) {
            CartVegetableDetails details=toCartVegetableDetails(vegetable);
            desired.add(details);
        }
        return desired;
    }

    public CartVegetableDetails toCartVegetableDetails(CartVegetable cartVegetable){
        CartVegetableDetails details=new CartVegetableDetails();
        Vegetable vegetable=cartVegetable.getVegetable();
        details.setVegId(vegetable.getVegId());
        details.setPrice(vegetable.getPrice());
        details.setCategory(vegetable.getCategory());
        details.setQuantity(cartVegetable.getQuantity());
        return details;
    }

    public CartDetails toDetails(Cart cart, List<CartVegetableDetails> vegetables) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setCartId(cart.getCartId());
        cartDetails.setCustId(cart.getCustId());
        cartDetails.setVegetables(vegetables);
        return cartDetails;
    }

}
