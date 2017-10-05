package com.deutschebank.assignment.service;




import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.validation.constraints.AssertTrue;

import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.deutschebank.assignment.AssignmentApplication;
import com.deutschebank.assignment.model.ErrorResponse;
import com.deutschebank.assignment.model.Shop;
import com.deutschebank.assignment.services.ShopServiceI;



@SpringBootTest(classes = ShopServiceTest.class)
public class ShopServiceTest {

	@MockBean
	private ShopServiceI shopService;
	
	@Before
	public void init() {		
		AssignmentApplication.setShopList(new ArrayList<Shop>());
	}
	
	@Test
	public void getShop(){
		Shop shop = new Shop();
		shop.setShopName("GSLab");
		shop.setShopNumber("12345");
		shop.setShopPinCode("411045");
		try{
			shopService.getShop(shop);
			fail("Should throw an exception");
		}catch(Exception e){
			
		}
	}
}
