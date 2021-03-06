package com.cg.vegetable.mgmt.dto;

import java.util.List;

import com.cg.vegetable.mgmt.entities.CartVegetable;
import com.cg.vegetable.mgmt.entities.Vegetable;

public class CartDetails {

    private int cartId;
    private int custId;
    private List<CartVegetableDetails> vegetables;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public List<CartVegetableDetails> getVegetables() {
        return vegetables;
    }

    public void setVegetables(List<CartVegetableDetails> vegetables) {
        this.vegetables = vegetables;
    }

}
