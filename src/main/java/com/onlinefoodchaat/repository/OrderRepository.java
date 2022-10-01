package com.onlinefoodchaat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinefoodchaat.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
 public List<Order> findByClientId(int id);

public List<Order> findByUserId(int userId);

public List<Order> findByUserIdAndClientId(int userId, int clientId);
}
