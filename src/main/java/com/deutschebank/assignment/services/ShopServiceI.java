package com.deutschebank.assignment.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.json.JSONException;

import com.deutschebank.assignment.model.Shop;

public interface ShopServiceI {

	public Shop addShop(Shop shop) throws IOException, JSONException, Exception;
	public Shop getShop(Shop shop) throws Exception;
}
