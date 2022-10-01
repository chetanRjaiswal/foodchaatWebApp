package com.onlinefoodchaat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity 
public class Order 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oid;
	private int clientId;
	private String dishName;
	private int quantity;
	private int totalAmount;
	private int userId;  
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_orderListId",referencedColumnName = "id")
	private OrderList orderList;
	
	public OrderList getOrderList() {
		return orderList;
	}

	public void setOrderList(OrderList orderList) {
		this.orderList = orderList;
	}

	public Order() {
		
	}

	public Order(int oid, int clientId, String dishName, int quantity, int totalAmount, int userId) {
		
		this.oid = oid;
		this.clientId = clientId;
		this.dishName = dishName;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.userId = userId;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", clientId=" + clientId + ", dishName=" + dishName + ", quantity=" + quantity
				+ ", totalAmount=" + totalAmount + "]";
	}
	
}
