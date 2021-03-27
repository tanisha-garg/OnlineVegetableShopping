package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableCategoryException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableNameException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetablePriceException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableTypeException;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;

@Component
public class OnlineVvegetableShoppingUI {
	
	@Autowired
	private IVegetableMgmtService vegetableService;
	
	public void start() {
		try {
			
			/*
			 *	Adding Vegetables 
			 */
			
			Vegetable tomato = vegetableService.addVegetable( new Vegetable("tomato","aboveground","yellowtomato",20.0,5));
			Vegetable potato = vegetableService.addVegetable( new Vegetable("potato","underground","Russet",15.0,10));
			Vegetable cauliFlower = vegetableService.addVegetable( new Vegetable("cauliFlower","aboveground","Brocolli",40.0,3));
			displayVegetableDetails(tomato);
			
			/*
			 * Update Vegetable
			 */
			
			potato.setPrice(25.0);
			Vegetable Updatedpotato = vegetableService.updateVegetable(potato);
			
			/*
			 * remove Vegetable
			 * 
			 */
			 System.out.print("----Displaying Removed Vegetable----");
			 //Vegetable removed = vegetableService.removeVegetable(cauliFlower);
			 //displayVegetableDetails(removed);
			 
			 /*
			  * view Vegetable
			  * 
			  */
			 
			 int tomatoId = tomato.getVegId();
			 Vegetable fetched = vegetableService.viewVegetable(tomatoId);
			 
		}catch(InvalidVegetableNameException e) {
			System.out.println(e.getMessage());
		}
		catch(InvalidVegetableCategoryException e) {
			System.out.println(e.getMessage());
		}
		catch(InvalidVegetableTypeException e) {
			System.out.println(e.getMessage());
		}
		catch(InvalidVegetablePriceException e) {
			System.out.println(e.getMessage());
		}
		catch(InvalidVegetableQuantityException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void displayVegetableDetails(Vegetable vegetable) {
		System.out.println("\nVeg ID:"+vegetable.getVegId()+"\nName:"+vegetable.getName()+"\nCategory"+vegetable.getCategory()+"\nType"+vegetable.getCategory()+
				"\nPrice:"+vegetable.getPrice()+"\nQuantity"+vegetable.getQuantity());
	}

}
