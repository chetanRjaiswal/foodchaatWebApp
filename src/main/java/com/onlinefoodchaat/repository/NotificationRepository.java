package com.onlinefoodchaat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchaat.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> 
{

	List<Notification> findByClientId(int clientId); 
	
}
