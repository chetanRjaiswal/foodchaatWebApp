package com.onlinefoodchaat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchaat.entity.Client;
import com.onlinefoodchaat.entity.OrderList;
import com.onlinefoodchaat.repository.ClientRepository;
import com.onlinefoodchaat.repository.OrderListRepository;

@Service
public class OrderListServiceImpl implements OrderListService {

	@Autowired
	private OrderListRepository listRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<OrderList> getOrderDetails(int clientId) {
		Client client=clientRepository.findById(clientId).orElse(null);
		String restoName=client.getRestoName();
		return listRepository.findByRestoName(restoName);
	}
}
