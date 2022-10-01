package com.onlinefoodchaat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userNotification;
	private String clientNotification;
	private int userId;
	private int clientId;
	private int oid;
	
	public Notification() {
		
	}

	public Notification(int id, String userNotification, String clientNotification, int userId, int clientId, int oid) {
		super();
		this.id = id;
		this.userNotification = userNotification;
		this.clientNotification = clientNotification;
		this.userId = userId;
		this.clientId = clientId;
		this.oid = oid;
	}

	public String getUserNotification() {
		return userNotification;
	}

	public void setUserNotification(String userNotification) {
		this.userNotification = userNotification;
	}

	public String getClientNotification() {
		return clientNotification;
	}

	public void setClientNotification(String clientNotification) {
		this.clientNotification = clientNotification;
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getClientId() {
		return clientId;
	}


	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}


	@Override
	public String toString() {
		return "Notification [userId=" + userId + ", clientId=" + clientId + "]";
	}
	
	
}
