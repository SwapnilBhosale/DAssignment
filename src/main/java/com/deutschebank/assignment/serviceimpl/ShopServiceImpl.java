package com.deutschebank.assignment.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.deutschebank.assignment.AssignmentApplication;
import com.deutschebank.assignment.constant.Constant;
import com.deutschebank.assignment.model.Shop;
import com.deutschebank.assignment.services.ShopServiceI;

@Service
public class ShopServiceImpl implements ShopServiceI {

	@Override
	public boolean addShop(Shop shop) throws IOException, JSONException {
		StringBuilder sb = new StringBuilder();
		sb.append(shop.getShopName()).append(",").append(shop.getShopNumber())
		.append(",").append(shop.getShopPinCode());
		URL url = new URL(Constant.GOOGLE_API_URL + URLEncoder.encode(sb.toString(), "UTF-8"));
		URLConnection conn = url.openConnection();  
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);  
		IOUtils.copy(conn.getInputStream(), output);  
		output.close();

		String s = output.toString();  
		System.out.println("Got google response : "+s);
		JSONObject json = new JSONObject(s);  
		if (!json.getString("status").equals("OK")) {  
		    return false;
		}
		JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
		shop.setLatitude(location.getDouble("lat"));
		shop.setLongitude(location.getDouble("lng"));
		
		AssignmentApplication.addShop(shop);
		AssignmentApplication.printList();
		return true;
		
		 
	}

	@Override
	public Shop getShop(Shop shop) {
		// TODO Auto-generated method stub
		return null;
	}

}
