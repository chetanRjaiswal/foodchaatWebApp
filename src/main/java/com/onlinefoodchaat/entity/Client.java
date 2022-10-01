package com.onlinefoodchaat.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(
uniqueConstraints = @UniqueConstraint(
			name = "email",
			columnNames = "email"
		)
)
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(name = "email",
			nullable = false
	)
	private String email;
	private String phone;
	private String password;
	private String plan;    
	private Integer otp;
	@Column(unique=true)
	private String restoName;
	
	private Date buyDate;
	private Date expireDate;

	@OneToMany(cascade = CascadeType.ALL) 
	private List<Dish> dishes;  
	
	public Client() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Client(int id, String name, String email, String phone, String password, String plan, Integer otp,
			String restoName, Date buyDate, Date expireDate, List<Dish> dishes) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.plan = plan;
		this.otp = otp;
		this.restoName = restoName;
		this.buyDate = buyDate;
		this.expireDate = expireDate;
		this.dishes = dishes;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public String getRestoName() {
		return restoName;
	}

	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	
	
	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", plan=" + plan + ", otp=" + otp + ", restoName=" + restoName + "]";
	}

}
