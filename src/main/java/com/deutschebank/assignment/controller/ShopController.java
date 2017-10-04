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
	public Response addNewShop(@RequestBody Shop shop) throws IOException, JSONException{
		if(checkEmptyAndNull(shop.getShopName()) || checkEmptyAndNull(shop.getShopNumber()) || checkEmptyAndNull(shop.getShopPinCode()))
			return new ErrorResponse("1", "Missing parameter");
		if(shopService.addShop(shop))
			return new SuccessResponse("Shop Added Successfully",null);
		else return new ErrorResponse("2", "Internal Server Error");
	}
	
	
	public boolean checkEmptyAndNull(String data){
		return (data == null || data.isEmpty());
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getShop(@RequestParam("customerLongitude") Double lang,@RequestParam("customerLatitude") Double lat){
		
		
	}
}
