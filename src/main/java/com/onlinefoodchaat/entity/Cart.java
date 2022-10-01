package com.onlinefoodchaat.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String dishName;
	private String dishPrice;
	private String dishImage;
	private Integer quantity;
	private Integer clientId;
	private String restoName;
	private Integer toalAmount;
	private Integer userId;

	
	public Cart() 
	{
		
	}
	

	public Cart(Integer id, String dishName, String dishPrice, String dishImage, Integer quantity, Integer clientId,
			Integer toalAmount, Integer userId, Integer sum) 
	{
		
		this.id = id;
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishImage = dishImage;
		this.quantity = quantity;
		this.clientId = clientId;
		this.toalAmount = toalAmount;
		this.userId = userId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(String dishPrice) {
		this.dishPrice = dishPrice;
	}

	public String getDishImage() {
		return dishImage;
	}

	public void setDishImage(String dishImage) {
		this.dishImage = dishImage;
	}

	public Integer getQuantity() {
		return quantity;   
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getToalAmount() {
		return toalAmount;
	}

	public void setToalAmount(Integer toalAmount) {
		this.toalAmount = toalAmount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRestoName() {
		return restoName;
	}

	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}

}
