package com.onlinefoodchaat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinefoodchaat.entity.OrderList;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer>
{

   public List<OrderList> findByRestoName(String restoName);

public List<OrderList> findByClientId(int clientId);
   
}  
