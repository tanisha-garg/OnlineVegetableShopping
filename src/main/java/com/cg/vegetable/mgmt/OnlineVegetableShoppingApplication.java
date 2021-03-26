package com.cg.vegetable.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cg.vegetable.mgmt.ui.OnlineVvegetableShoppingUI;

@SpringBootApplication
public class OnlineVegetableShoppingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OnlineVegetableShoppingApplication.class, args);
		OnlineVvegetableShoppingUI shoppingUI = context.getBean(OnlineVvegetableShoppingUI.class);
		shoppingUI.start();
	}

}