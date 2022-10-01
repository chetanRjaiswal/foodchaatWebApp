package com.onlinefoodchaat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchaat.entity.Cart;
import com.onlinefoodchaat.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
  public List<Cart> findByUserId(int userId);
  public Cart findByDishNameAndUserIdAndClientId(String dishName,int userId,int clientId);
  public List<Cart> findByUserIdAndClientId(int userId,int clientId);
  public List<Cart> findByClientId(int clientId);
  public void deleteByUserId(int userId);
  
}
