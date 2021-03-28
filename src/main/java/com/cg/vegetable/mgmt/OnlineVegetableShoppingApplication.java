package com.cg.vegetable.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cg.vegetable.mgmt.ui.BillingDetailsUI;
import com.cg.vegetable.mgmt.ui.CartUI;
import com.cg.vegetable.mgmt.ui.CustomerUI;
import com.cg.vegetable.mgmt.ui.OrderUI;
import com.cg.vegetable.mgmt.ui.VegetableUI;

@SpringBootApplication
public class OnlineVegetableShoppingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OnlineVegetableShoppingApplication.class, args);
		
		CustomerUI customerUI = context.getBean(CustomerUI.class);
		customerUI.start();
		
		VegetableUI vegetableUI = context.getBean(VegetableUI.class);
		vegetableUI.start();
		
		OrderUI orderUI = context.getBean(OrderUI.class);
		orderUI.start();
		
		BillingDetailsUI billingUi = context.getBean(BillingDetailsUI.class);
		billingUi.start();
		
		
		CartUI cartUI = context.getBean(CartUI.class);
		cartUI.start();
	
	}

}
