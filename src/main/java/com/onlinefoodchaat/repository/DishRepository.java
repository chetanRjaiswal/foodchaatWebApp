package com.onlinefoodchaat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinefoodchaat.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer>
{
	public List<Dish> findAllByClientId(Integer clientId);
}
