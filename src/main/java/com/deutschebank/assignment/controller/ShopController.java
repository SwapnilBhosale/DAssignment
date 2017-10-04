package com.deutschebank.assignment.controller;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.assignment.AssignmentApplication;
import com.deutschebank.assignment.model.ErrorResponse;
import com.deutschebank.assignment.model.Response;
import com.deutschebank.assignment.model.Shop;
import com.deutschebank.assignment.model.SuccessResponse;
import com.deutschebank.assignment.services.ShopServiceI;
import com.google.maps.model.LocationType;

@RestController
public class ShopController {

	@Autowired
	private ShopServiceI shopService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addNewShop(@RequestBody Shop shop){
		try{
			if(checkEmptyAndNull(shop.getShopName()) || checkEmptyAndNull(shop.getShopNumber()) || checkEmptyAndNull(shop.getShopPinCode()))
				return new ErrorResponse("1", "Missing parameter");
			Shop s =null;
			if((s=shopService.addShop(shop)) != null)
				return new SuccessResponse("Shop Added Successfully",s);
			else 
				return new ErrorResponse("2", "Could not add shop.");
		}catch(Exception e){
			return new ErrorResponse("3", e.getMessage());
		}
	}
	
	
	public boolean checkEmptyAndNull(String data){
		return (data == null || data.isEmpty());
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getShop(@RequestParam("customerLongitude") Double lng,@RequestParam("customerLatitude") Double lat) throws Exception{
		try{
			if(lng == null || lat == null)
				return new ErrorResponse("1", "Missing parameter");
			Shop shop = new Shop();
			shop.setLatitude(lat);
			shop.setLongitude(lng);
			shop = shopService.getShop(shop);
			if(shop != null)
				return new SuccessResponse("Nearest shop found.", shop);
			else 
				return new ErrorResponse("2", "Error finding nearest lcoation");
		}catch(Exception e){
			return new ErrorResponse("3", e.getMessage());
		}
			
	}
}
