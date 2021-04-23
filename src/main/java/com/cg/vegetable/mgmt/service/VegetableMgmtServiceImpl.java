package com.cg.vegetable.mgmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.cg.vegetable.mgmt.constants.VegetableCategory;
import com.cg.vegetable.mgmt.constants.VegetableType;
import com.cg.vegetable.mgmt.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;

@Service
public class VegetableMgmtServiceImpl implements IVegetableMgmtService {

    @Autowired
    IVegetableMgmtRepository vegetableRepository;


    /*
     * scenario : Vegetable saved successfully in database
     * input: Vegetable type argument
     * expectation:Vegetable should be added in database after validation
     *
     */

    @Override
    public Vegetable addVegetable(Vegetable vegetable) {
        validateVegetable(vegetable);
        String category= vegetable.getCategory().toLowerCase();
        vegetable.setCategory(category);
        String type= vegetable.getType().toLowerCase();
        vegetable.setType(type);
        return vegetableRepository.save(vegetable);
    }



    /*
     * scenario :   UpdatedVegetable saved successfully in database
     * input:       Vegetable type argument after updation
     * expectation: UpdatedVegetable should be added in database after validation
     *
     */

    @Override
    public Vegetable updateVegetable(Vegetable vegetable) {
        validateVegetable(vegetable);
        int vegId = vegetable.getVegId();
        boolean exists = vegetableRepository.existsById(vegId);
        if (!exists) {
            throw new VegetableNotFoundException("Vegetable not found with id" + vegId);
        }
        Vegetable saved = vegetableRepository.save(vegetable);
        return saved;
    }

    /*
     * scenario :   Vegetable removed from database
     * input:       Vegetable type argument which has to be removed
     * expectation: Vegetable to be removed from database
     *
     */

    @Override
    public Vegetable removeVegetable(Vegetable vegetable) {
        int vegId = vegetable.getVegId();
        boolean exists = vegetableRepository.existsById(vegId);
        if (!exists) {
            throw new VegetableNotFoundException("No vegetable found");
        }
        vegetableRepository.deleteById(vegId);
        return vegetable;
    }

    /*
     * scenario :   Vegetable to be fetched from database after validation
     * input:       Vegetable id of integer type argument which has to be fetched
     * expectation: Vegetable to be fetched from database
     *
     */

    @Override
    public Vegetable viewVegetable(int vegId) {
        validateId(vegId);
        Optional<Vegetable> optional = vegetableRepository.findById(vegId);
        if (!optional.isPresent())
            throw new VegetableNotFoundException("Vegetable does not exist  for given id " + vegId);
        return optional.get();
    }

    @Override
    public List<Vegetable> viewAllVegetables() {
        List<Vegetable> allVegetables = vegetableRepository.findAll();
        return allVegetables;
    }

    @Override
    public List<Vegetable> viewVegetableByCategory(String category) {
        validateCategory(category);
        List<Vegetable> allVegetables = vegetableRepository.findByCategory(category);
        return allVegetables;
    }

    @Override
    public List<Vegetable> viewVegetableByName(String name) {
        validateCategory(name);
        List<Vegetable> allVegetables = vegetableRepository.findByName(name);
        return allVegetables;
    }

    public void validateVegetable(Vegetable vegetable) {
        validateName(vegetable.getName());
        validateCategory(vegetable.getCategory());
        validateType(vegetable.getType());
        validatePrice(vegetable.getPrice());
        validateQuantity(vegetable.getQuantity());
    }

    public void validateName(String name) {
        if (name.isEmpty() || name == null || name.trim().isEmpty())
            throw new InvalidVegetableNameException("name can't empty or null");
    }

    public void validateCategory(String category) {
        if (category.isEmpty() || category == null || category.trim().isEmpty()) {
            throw new InvalidVegetableCategoryException("category can't empty or null");
        }

        if (!category.equalsIgnoreCase(VegetableCategory.AboveGround) &&
                !category.equalsIgnoreCase(VegetableCategory.UnderGround)) {
            throw new InvalidCategoryException("category is invalid");
        }


    }

    public void validateType(String type) {
        if (type.isEmpty() || type == null || type.trim().isEmpty()) {
            throw new InvalidVegetableTypeException("type can't empty or null");
        }
        if (!type.equalsIgnoreCase(VegetableType.Allium) &&
                !type.equalsIgnoreCase(VegetableType.Cruciferous)&& !type.equalsIgnoreCase(VegetableType.LeafyGreen)&&
                !type.equalsIgnoreCase(VegetableType.Marrow) && !type.equalsIgnoreCase(VegetableType.Root)) {
            throw new InvalidCategoryException("type is invalid");
        }
        
    }

    public void validatePrice(double price) {
        if (price <= 0)
            throw new InvalidVegetablePriceException("price can't be negative");
    }

    public void validateQuantity(int quantity) {
        if (quantity < 0)
            throw new InvalidVegetableQuantityException("Quantity can't be negative");
    }

    public void validateId(int id) {
        if (id < 0)
            throw new InvalidVegetableIdException("Id can't be negative");
    }

}
