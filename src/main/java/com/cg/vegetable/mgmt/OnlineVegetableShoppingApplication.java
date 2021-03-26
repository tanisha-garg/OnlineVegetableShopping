package com.cg.vegetable.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cg.vegetable.mgmt.ui.OnlineVegetableShoppingUI;

@SpringBootApplication
public class OnlineVegetableShoppingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OnlineVegetableShoppingApplication.class, args);
		OnlineVegetableShoppingUI shoppingUI = context.getBean(OnlineVegetableShoppingUI.class);
		shoppingUI.start();
		
	}

}
