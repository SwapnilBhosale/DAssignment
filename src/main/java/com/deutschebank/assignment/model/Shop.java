package com.deutschebank.assignment.model;

public class Shop {
	
	private String shopName;
	private String shopNumber;
	private String shopPinCode;
	private Double longitude;
	private Double latitude;
	
	
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopNumber() {
		return shopNumber;
	}
	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}
	public String getShopPinCode() {
		return shopPinCode;
	}
	public void setShopPinCode(String shopPinCode) {
		this.shopPinCode = shopPinCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shop [shopName=");
		builder.append(shopName);
		builder.append(", shopNumber=");
		builder.append(shopNumber);
		builder.append(", shopPinCode=");
		builder.append(shopPinCode);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
