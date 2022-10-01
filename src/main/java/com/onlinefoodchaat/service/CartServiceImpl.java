package com.onlinefoodchaat.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchaat.entity.Cart;
import com.onlinefoodchaat.entity.Dish;
import com.onlinefoodchaat.entity.User;
import com.onlinefoodchaat.repository.CartRepository;
import com.onlinefoodchaat.repository.DishRepository;
import com.onlinefoodchaat.repository.UserRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Cart saveTocart(int dishId, int userId, int clientId, int quantity) {
		Cart cart = new Cart();
		
		try {
			Dish dish = dishRepository.findById(dishId).orElse(null);
			List<Cart> listOfCart = cartRepository.findByUserId(userId);
			if (listOfCart.size()>0) {
				boolean status = true;
				for (Cart c : listOfCart) 
				{
					if (c.getClientId() == clientId) 
					{
						status=false;
						break;
					}
				}
				if(status)
					return null;//System.out.println("Please first remove another restroaent....");
				else
					cart = finalAddtoCart(dish, userId, clientId, quantity);

			} else
				cart = finalAddtoCart(dish, userId, clientId, quantity);
			
			return cart;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Cart finalAddtoCart(Dish dish, int userId, int clientId, int quantity) {

		Cart cart = new Cart();
		// checking if already added a record with particular dishName and clientId by
		// user
		Cart isAdded = cartRepository.findByDishNameAndUserIdAndClientId(dish.getDishName(), userId, clientId);

		// if isAdded null means no record added to cart so add record to cart
		if (isAdded == null) {
			cart.setDishName(dish.getDishName());
			cart.setDishPrice(dish.getDishPrice());
			cart.setDishImage(dish.getDishImg());
			cart.setQuantity(quantity);
			cart.setClientId(clientId);
			cart.setUserId(userId);
			cart.setToalAmount(Integer.parseInt(dish.getDishPrice()) * quantity);
			Cart savedtoCart = cartRepository.save(cart);
			return savedtoCart;
		}

		// if isAdded not null means record is already added to cart so update cart
		isAdded.setQuantity(isAdded.getQuantity() + quantity);
		isAdded.setToalAmount(Integer.parseInt(isAdded.getDishPrice()) * (isAdded.getQuantity()));
		cartRepository.save(isAdded);
		return isAdded;

	}

	@Override
	public List<Cart> getByUserId(int userId) {
		return cartRepository.findByUserId(userId);
	}

	@Override
	public Cart updateCartById(int cartId, int quantity) {
		Cart cart = new Cart();
		try {
			cart = cartRepository.findById(cartId).orElse(null);
			cart.setQuantity(quantity);
			cart.setToalAmount(Integer.parseInt(cart.getDishPrice()) * cart.getQuantity());
			cartRepository.save(cart);
			return cart;
		} catch (Exception e) {
			e.getMessage();
		}
		return cart;
	}

	@Override
	public Cart degradeCartById(int cartId, int quantity) {
		Cart cart = new Cart();
		try {
			cart = cartRepository.findById(cartId).orElse(null);
			cart.setQuantity(quantity);
			cart.setToalAmount(Integer.parseInt(cart.getDishPrice()) * cart.getQuantity());
			cartRepository.save(cart);
			return cart;
		} catch (Exception e) {
			e.getMessage();
		}
		return cart;
	}

	@Override
	public Boolean deleteByCartId(int cartId) {
		Boolean f = false;
		try {
			cartRepository.deleteById(cartId);
			// Cart cart=cartRepository.findById(cartId).get();
			// cartRepository.deleteByIdAndUser(cart.getId(),cart.getUser());
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public int getTotalCartSum(int userId) {
		int sum=0;
		List<Cart> cart=cartRepository.findByUserId(userId);
		for(Cart c : cart) {
			sum=sum+c.getToalAmount();
		}
		return sum;
	}
}
