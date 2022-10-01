package com.onlinefoodchaat.service;

import java.util.List;

import com.onlinefoodchaat.entity.Cart;

public interface CartService {
  public Cart saveTocart(int dishId,int userId,int clientId,int quantity);
  public List<Cart> getByUserId(int userId);
  public Cart updateCartById(int cartId,int quantity);
  public Cart degradeCartById(int cartId, int quantity);
  public Boolean deleteByCartId(int cartId);
  public int getTotalCartSum(int userId);
}
	   