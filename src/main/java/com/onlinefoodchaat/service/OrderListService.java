package com.onlinefoodchaat.service;

import java.util.List;

import com.onlinefoodchaat.entity.OrderList;

public interface OrderListService {

	public List<OrderList>getOrderDetails(int clientId);
}
