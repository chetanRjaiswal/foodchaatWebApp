package com.onlinefoodchaat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchaat.entity.Cart;

import com.onlinefoodchaat.entity.User;

import com.onlinefoodchaat.service.CartService;


@RestController
public class CartController 
{
	@Autowired
	private CartService cartService;
	
	@PostMapping("/saveToCart")
	public ResponseEntity<ModelAndView> addTocart(@RequestParam int dishId,@RequestParam int userId,@RequestParam int clientId,@RequestParam int quantity)
	{
		System.out.println("savecartcontroller");
		System.out.println("dishId="+dishId+"userId="+userId+"clientID="+clientId+"quan="+quantity);
		ModelAndView mav = new ModelAndView();
		Cart cart = cartService.saveTocart(dishId,userId,clientId,quantity);
		
		if(cart!=null) {
			mav.setViewName("viewMenu");
			mav.addObject("cartUpdateMsg","Cart Updated Successfully");
			return ResponseEntity.status(HttpStatus.OK).body(mav);
		}
		mav.addObject("CartErrMsg","Unable to Add to Cart");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mav);
	}
	
	@GetMapping("/getCart")
	public ModelAndView getCartByuserId(HttpServletRequest req)
	{
		ModelAndView modelAndView = new ModelAndView();
		Integer sum=0;
		try {
			User user=(User)req.getSession().getAttribute("loginUserObj");
			List<Cart> cartList=cartService.getByUserId(user.getId());
			System.out.println(cartList);
			sum=cartService.getTotalCartSum(user.getId());
			modelAndView.addObject("listOfCart",cartList);
			modelAndView.addObject("totalSum",sum);
			modelAndView.setViewName("Cart");
			return modelAndView;
		}
		catch (Exception e) {
			e.getMessage();
		}
		return modelAndView; 
	}
	
	
	@PostMapping("/updgradeCart")
	public ModelAndView upgradeCart(@RequestParam int cartId,@RequestParam int quantity,HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		int sum=0;
		int userId = (Integer)req.getSession().getAttribute("loginUserId");
		System.out.println("cartId "+cartId+" quantity "+quantity);
		cartService.updateCartById(cartId, quantity);
		sum=cartService.getTotalCartSum(userId);
		mav.addObject("totalSum",sum);
		mav.setViewName("redirect:/getCart");
		return mav;
	}
	
	@PostMapping("/degradeCart")
	public ModelAndView degradeCart(@RequestParam int cartId,@RequestParam int quantity,HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		int userId=(Integer)req.getSession().getAttribute("loginUserId");
		int sum=0;
		System.out.println("cartId "+cartId+" quantity "+quantity);
		cartService.degradeCartById(cartId, quantity);
		sum=cartService.getTotalCartSum(userId);
		mav.addObject("totalSum",sum);
		mav.setViewName("redirect:/getCart");
		return mav;
	}

	@PostMapping("/deleteBycartId")
	public ModelAndView deleteFromCart(@RequestParam int cartId)
	{
		ModelAndView mav = new ModelAndView();
		System.out.println(cartId);
		cartService.deleteByCartId(cartId);
		mav.setViewName("redirect:/getCart");      
		return mav;
	}
	
}
