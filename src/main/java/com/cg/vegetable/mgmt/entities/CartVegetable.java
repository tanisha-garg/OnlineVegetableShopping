package com.cg.vegetable.mgmt.entities;

import javax.persistence.*;

@Entity
public class CartVegetable {

    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Vegetable vegetable;

    private int quantity;


    public CartVegetable(){

    }
    // 1 , cartid-1 , vegid-1 , 2
    // 2  ,cartid-1, vegid-2, 3
    // 3  ,cartid-2, veg-1, 6
    //
    public CartVegetable(Cart cart, Vegetable vegetable, int quantity){

    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
