package com.cg.vegetable.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cg.vegetable.mgmt.ui.BillingDetailsUI;
import com.cg.vegetable.mgmt.ui.CartUI;
import com.cg.vegetable.mgmt.ui.CustomerUI;
import com.cg.vegetable.mgmt.ui.FeedbackUI;
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
		
		FeedbackUI feedbackUI = context.getBean(FeedbackUI.class);
		feedbackUI.start();		
	
	}
	 /**
    *
    * for handling cross origin requests
    */
   @Bean
   public CorsFilter corsFilter(){
       UrlBasedCorsConfigurationSource src=new UrlBasedCorsConfigurationSource();
       CorsConfiguration configuration=new CorsConfiguration();
       configuration.setAllowCredentials(true);
       configuration.addAllowedHeader("*");
       configuration.addAllowedOrigin("http://localhost:3000");
       configuration.addAllowedMethod("*");
       src.registerCorsConfiguration("/**",configuration);
       return new CorsFilter(src);
   }

}
