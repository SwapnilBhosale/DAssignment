package com.deutschebank.assignment.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.json.JSONException;

import com.deutschebank.assignment.model.Shop;

public interface ShopServiceI {

	public boolean addShop(Shop shop) throws IOException, JSONException;
	public Shop getShop(Shop shop);
}
