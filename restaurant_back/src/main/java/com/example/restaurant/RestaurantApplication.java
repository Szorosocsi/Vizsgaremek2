package com.example.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 3 annotáció egyben: @Configuration, @EnableAutoConfiguration, @ComponentScan
// @ComponentScan: megkeresi a komponenseket: @Controller, @Service, @Repository, @Component, @Entity
// @EnableAutoConfiguration: libray-ket keres a projektben. config-ot keres az application.properties-ben.
//  -> @Configuration: automazikusan konfigurálja. 
// 
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
}
