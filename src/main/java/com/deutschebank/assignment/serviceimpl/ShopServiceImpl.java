package com.deutschebank.assignment.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.deutschebank.assignment.AssignmentApplication;
import com.deutschebank.assignment.constant.Constant;
import com.deutschebank.assignment.model.Shop;
import com.deutschebank.assignment.services.ShopServiceI;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class ShopServiceImpl implements ShopServiceI {

	@Autowired
	private GeoApiContext ctx;
	
	@Override
	public Shop addShop(Shop shop) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(shop.getShopName()).append(",").append(shop.getShopNumber())
		.append(",").append(shop.getShopPinCode());
	
		ctx.setApiKey(Constant.GEOCODING_API_KEY);
		GeocodingResult result = GeocodingApi.geocode(ctx, sb.toString()).await()[0];
		
		
		if(ObjectUtils.isEmpty(result))
			return null;	
		
		shop.setLatitude(result.geometry.location.lat);
		shop.setLongitude(result.geometry.location.lng);
		AssignmentApplication.addShop(shop);
		AssignmentApplication.printList();
		return shop;
		
		 
	}

	@Override
	public Shop getShop(Shop shop) throws Exception {
		// TODO Auto-generated method stub
		
		List<Shop> myList = AssignmentApplication.getShopList();
		if (myList.size() == 0)
			throw new Exception("No shops are in found in DB.");
		LatLng[] latLng = new LatLng[myList.size()];
		int i=0;
		for(Shop s : myList){
			latLng[i++] = new LatLng(s.getLatitude(), s.getLongitude());
		}
		ctx.setApiKey(Constant.DISTANCE_MATRIX_API_KEY);
		DistanceMatrix distance = DistanceMatrixApi.newRequest(ctx).origins(new LatLng(shop.getLatitude(), shop.getLongitude())).destinations(latLng).await();
		DistanceMatrixRow row = distance.rows[0];
		Long minDistance = Long.MAX_VALUE;
		int minIndex = 0;
		i=0;
		for(DistanceMatrixElement element : row.elements){
			Long dis = element.distance.inMeters;
			System.out.println("orig Dis : "+dis);
			if(dis <= minDistance){
				System.out.println("MinDistance "+minDistance+" dis : "+dis+" i : "+i);
				minDistance = dis;
				minIndex = i;
			}
			i++;
		}
		System.out.println("*** distance found : "+minIndex+" size : "+row.elements.length);
		return myList.get(minIndex);
	}
}
