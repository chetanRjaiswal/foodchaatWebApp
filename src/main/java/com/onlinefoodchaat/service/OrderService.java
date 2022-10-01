package com.onlinefoodchaat.service;


import java.util.List;

import com.onlinefoodchaat.entity.Order;

public interface OrderService {
public Order saveOrder(int clientId,String dishName,int quantity,int totalAmount,int userId,int cartId);
public List<Order>  getAllOrders(int clientId);
public Boolean placeAllOrders(int userId,int clientId);
public List<Order> getOrderByUserIdAndClientId(int userId,int clientId);
}
