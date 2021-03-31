package com.cg.vegetable.mgmt.dto;

import java.util.Objects;

public class CartVegetableDetails {
    private int vegId;
    private String name;
    private String type;
    private String category;
    private double price;
    private int quantity;

    public int getVegId() {
        return vegId;
    }

    public void setVegId(int vegId) {
        this.vegId = vegId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVegetableDetails that = (CartVegetableDetails) o;
        return vegId == that.vegId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vegId);
    }
}
