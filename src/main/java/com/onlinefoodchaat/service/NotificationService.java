package com.onlinefoodchaat.service;

import java.util.List;

import com.onlinefoodchaat.entity.Notification;

public interface NotificationService 
{
   public List<Notification> getNotification(int clientId);
}
