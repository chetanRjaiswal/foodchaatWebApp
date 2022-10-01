package com.onlinefoodchaat.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

@Entity
public class OrderList 
{
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String restoName;
  private int quantity;
  private int sum;
  private String userName;
  private int userId;
  private int clientId;
  
  
  @OneToMany(cascade = CascadeType.ALL) 
  private List<Order> order;


  public OrderList() {
		
  }


  public OrderList(int id, String restoName, int quantity, int sum, List<Order> order) {
		this.id = id;
		this.restoName = restoName;
		this.quantity = quantity;
		this.sum = sum;
		this.order = order;
  }

  

	public int getClientId() {
	return clientId;
}


public void setClientId(int clientId) {
	this.clientId = clientId;
}


	public int getUserId() {
	return userId;
}


public void setUserId(int userId) {
	this.userId = userId;
}


	public String getUserName() {
	return userName;
}


public void setUserName(String userName) {
	this.userName = userName;
}


public List<Order> getOrder() {
	return order;
}


public void setOrder(List<Order> order) {
	this.order = order;
}


	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getRestoName() {
		return restoName;
	}
	
	
	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public int getSum() {
		return sum;
	}
	
	
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
	public List<Order> getorder() {
		return order;
	}
	
	
	public void setorder(List<Order> order) {
		this.order = order;
	}
   
}
