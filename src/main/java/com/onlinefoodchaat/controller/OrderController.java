package com.onlinefoodchaat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchaat.entity.Order;
import com.onlinefoodchaat.entity.OrderList;
import com.onlinefoodchaat.service.OrderListService;
import com.onlinefoodchaat.service.OrderService;

@RestController
public class OrderController 
{
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderListService orderListService;
	
	@PostMapping("/placeOrder")
	public ModelAndView placeOrder(@RequestParam int clientId,@RequestParam String dishName,
			@RequestParam int quantity,@RequestParam int totalAmount,@RequestParam int cartId,HttpServletRequest req)
	{
		int userId = (Integer)req.getSession().getAttribute("loginUserId");
		ModelAndView mav= new ModelAndView();
		Order order = new Order();
		System.out.println("clientId"+clientId+"dish"+dishName+"quan"+quantity+"total"+totalAmount+"cartId"+cartId);
		order=orderService.saveOrder(clientId, dishName, quantity, totalAmount,userId,cartId);
		if(order!=null) {
		
		mav.setViewName("redirect:/getCart");
		}
		return mav;
	}
	
	@PostMapping("/buy")
	public ModelAndView placeOrders(@RequestParam int finalSum , HttpServletRequest req)
	{
		int clientId = (Integer) req.getSession().getAttribute("CartClientId");
		System.out.println("on buy clientId"+clientId);
		int userId = (Integer) req.getSession().getAttribute("loginUserId");
		ModelAndView mav = new ModelAndView();
	
		if(orderService.placeAllOrders(userId,clientId)) 
		{
			mav.addObject("OrderMsg","Order Success");
			mav.setViewName("redirect:/getCart");
			return mav;
		}
		mav.addObject("OrderFail","Order Failed");
		mav.setViewName("redirect:/getCart");
		return mav;
	}
	
	@GetMapping("/viewOrders")
	public ModelAndView viewOrders(HttpServletRequest req)
	{
		
	  ModelAndView mav = new ModelAndView();
	  HttpSession session = req.getSession();
	  int clientId=(Integer)session.getAttribute("loginClientId");
	  System.out.println("clientiD"+clientId);
	  List<OrderList> orderList=orderListService.getOrderDetails(clientId);
	  mav.addObject("OrderList", orderList);
	  mav.setViewName("viewOrder");
	  return mav;
	  
	}
	
	@GetMapping("/viewOrderMenu")
	public ModelAndView viewOrderMenu(@RequestParam int userId,HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		int clientId= (Integer)req.getSession().getAttribute("loginClientId");
		List<Order> ordersByUserId = orderService.getOrderByUserIdAndClientId(userId,clientId);
		mav.addObject("ordersByUser", ordersByUserId);
		mav.setViewName("viewMenuOrder");
		return mav;
	}
	
}
