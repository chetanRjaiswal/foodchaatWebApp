package com.onlinefoodchaat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
uniqueConstraints = @UniqueConstraint(
			name = "dishName",
			columnNames = "dishName"
		)
)
public class Dish {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String dishName;
	private String dishPrice;
	private String dishImg;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_clientId",referencedColumnName = "id")
	private Client client;
	
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
	public String getDishImg() {
		return dishImg;
	}
	public void setDishImg(String dishImg) {
		this.dishImg = dishImg;
	}
	
	public Dish() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Dish(String dishName, String dishPrice, String dishImg) {
		super();
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishImg = dishImg;
	}
	
	@Override
	public String toString() {
		return "Dish [id=" + id + ", dishName=" + dishName + ", dishPrice=" + dishPrice + ", dishImg=" + dishImg + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
