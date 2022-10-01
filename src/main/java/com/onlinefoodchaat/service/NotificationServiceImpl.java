package com.onlinefoodchaat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchaat.entity.Notification;
import com.onlinefoodchaat.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
 
 @Autowired
 private NotificationRepository notificationRepository; 
 
 @Override
 public List<Notification> getNotification(int clientId) {
	List<Notification> nos=notificationRepository.findByClientId(clientId);
	return nos;
 }
}
