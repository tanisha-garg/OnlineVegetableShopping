package com.cg.vegetable.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cg.vegetable.mgmt.ui.OnlineVegetableShoppingUI;

@SpringBootApplication
public class OnlinevegetableshoppingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(OnlinevegetableshoppingApplication.class, args);
		OnlineVegetableShoppingUI onlineVegetableShoppingUI = context.getBean(OnlineVegetableShoppingUI.class);
		onlineVegetableShoppingUI.start();
	}

}