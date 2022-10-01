package com.onlinefoodchaat.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.onlinefoodchaat.entity.Dish;

public interface DishService {
	
	public Boolean addDish(String dishName,String dishPrice,MultipartFile file,int clientId);
	public List<Dish> getAllDishByClientId(Integer clientId);
	public Dish getDishById(Integer dishId);
	public Boolean editDish(Integer dishId,String dishName,String dishPrice,MultipartFile file);
	public Boolean deleteDishById(Integer dishId);
}
