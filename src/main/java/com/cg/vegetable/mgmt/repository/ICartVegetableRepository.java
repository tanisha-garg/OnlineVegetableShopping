package com.cg.vegetable.mgmt.repository;

import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.CartVegetable;
import com.cg.vegetable.mgmt.entities.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICartVegetableRepository extends JpaRepository<CartVegetable, Long> {
    @Query("Select vegetable from CartVegetable where cart=:cart")
    List<Vegetable> findVegetablesByCart(@Param("cart") Cart cart);

    List<CartVegetable>findByCart(Cart cart);

   CartVegetable findCartVegetableByCartAndVegetable(Cart cart, Vegetable vegetable);

    boolean existsByCartAndVegetable(Cart cart, Vegetable vegetable);

    void deleteByCart(Cart cart);

    void deleteByCartAndVegetable(Cart cart, Vegetable vegetable);

}
