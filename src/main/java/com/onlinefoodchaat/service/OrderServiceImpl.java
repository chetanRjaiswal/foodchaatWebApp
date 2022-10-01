package com.onlinefoodchaat.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.onlinefoodchaat.entity.Cart;
import com.onlinefoodchaat.entity.Client;
import com.onlinefoodchaat.entity.Notification;
import com.onlinefoodchaat.entity.Order;
import com.onlinefoodchaat.entity.OrderList;
import com.onlinefoodchaat.entity.User;
import com.onlinefoodchaat.repository.CartRepository;
import com.onlinefoodchaat.repository.ClientRepository;
import com.onlinefoodchaat.repository.NotificationRepository;
import com.onlinefoodchaat.repository.OrderListRepository;
import com.onlinefoodchaat.repository.OrderRepository;
import com.onlinefoodchaat.repository.UserRepository;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderListRepository orderListRepository;

	@Override
	public Order saveOrder(int clientId, String dishName, int quantity, int totalAmount, int userId, int cartId) {
		Order order = new Order();
		try {
			order.setClientId(clientId);
			order.setDishName(dishName);
			order.setQuantity(quantity);
			order.setTotalAmount(totalAmount);
			order.setUserId(userId);
			order = orderRepository.save(order);
			
			Notification notify = new Notification();
			
			notify.setOid(order.getOid());

			notify.setClientId(clientId);
			notify.setClientNotification("you received order by user"
					+ userRepository.findById(userId).get().getFullName() + "with order id" + order.getOid());

			notify.setUserId(userId);
			notificationRepository.save(notify);

			cartRepository.deleteById(cartId);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> getAllOrders(int clientId) {
		List<Order> orders = orderRepository.findByClientId(clientId);
		return orders;
	}

	@Transactional
	@Override
	public Boolean placeAllOrders(int userId,int clientId) 
	{
		boolean flag = false;
		Client  client =  clientRepository.findById(clientId).orElse(null);
		User user = userRepository.findById(userId).orElse(null); 
		try 
		{
			List<Cart> findAllCart = cartRepository.findByUserId(userId);
			List<Order> orders = new ArrayList<Order>();
			int quantity=0,sum=0;
			for (Cart c : findAllCart) {
				Order order = new Order();
				order.setClientId(c.getClientId());
				order.setDishName(c.getDishName());
				order.setQuantity(c.getQuantity());
				quantity+=c.getQuantity();
				order.setTotalAmount(c.getToalAmount());
				sum+=order.getTotalAmount();
				order.setUserId(userId);
				orders.add(order);
		     }
			orderRepository.saveAll(orders);
			OrderList ordersList = new OrderList();
			
			ordersList.setRestoName(client.getRestoName());
			ordersList.setQuantity(quantity);
			ordersList.setSum(sum);
			ordersList.setorder(orders);
			ordersList.setUserName(user.getFullName());
			ordersList.setUserId(userId);
			orderListRepository.save(ordersList);
			
			flag = true;
			cartRepository.deleteByUserId(userId);
			System.out.println("deletebyId");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public List<Order> getOrderByUserIdAndClientId(int userId,int clientId) {
		return orderRepository.findByUserIdAndClientId(userId,clientId);
	}

}
