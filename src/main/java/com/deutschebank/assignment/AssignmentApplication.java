package com.deutschebank.assignment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deutschebank.assignment.model.Shop;

@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

	static List<Shop> shopList;
	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		shopList = new ArrayList<Shop>();
	}
	
	
	public static void addShop(Shop shop){
		shopList.add(shop);
	}
	
	
	public static void printList(){
		System.out.println("**** " +shopList);
	}
}
