package com.onlinefoodchaat.service;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlinefoodchaat.entity.Client;
import com.onlinefoodchaat.entity.Dish;
import com.onlinefoodchaat.repository.DishRepository;


@Service
public class DishServiceImpl implements DishService
{

	public final String Upload_Dir="C:\\Users\\Dell\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\online-food-chaat-Assessment-3\\src\\main\\resources\\static\\DishImage";
	
	@Autowired
	 private DishRepository dishRepository; 
	
	@Override
	public Boolean addDish(String dishName, String dishPrice, MultipartFile file,int clientId) {
	
		Boolean f=false;
		Dish dish= new Dish();
		
		try {
			
			InputStream imageStream = file.getInputStream();
			byte data[] = new byte[imageStream.available()];
			imageStream.read(data);
			
			FileOutputStream fos = new FileOutputStream(Upload_Dir+File.separator+file.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();
			//set the dish properties
			
			Client c=new Client();
			c.setId(clientId);
			
			dish.setDishName(dishName);
			dish.setDishPrice(dishPrice);
			dish.setDishImg(file.getOriginalFilename());
			dish.setClient(c);
			

			//save the dish in db
			dishRepository.save(dish);
			
			f=true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public List<Dish> getAllDishByClientId(Integer clientId) {
		return dishRepository.findAllByClientId(clientId);
	}

	@Override
	public Boolean editDish(Integer dishId, String dishName, String dishPrice, MultipartFile file) 
	{
		Dish dish = dishRepository.findById(dishId).orElse(null);
		Boolean f=false;
		
		System.out.println("in edit dish service method");
		System.out.println(dish);
		System.out.println("file ------------");
		System.out.println(file.isEmpty());
		
		try {
			if(!file.isEmpty())
			{
				System.out.println("inside if");
				InputStream imageStream = file.getInputStream();
				byte data[] = new byte[imageStream.available()];
				imageStream.read(data);
				
				FileOutputStream fos = new FileOutputStream(Upload_Dir+File.separator+file.getOriginalFilename());
				fos.write(data);
				fos.flush();
				fos.close();
				dish.setDishImg(file.getOriginalFilename());
			}
			//set the dish properties
			
			dish.setDishName(dishName);
			dish.setDishPrice(dishPrice);
			
			System.out.println("before save");
			

			//save the dish in db
			dishRepository.save(dish);
			
			f=true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public Dish getDishById(Integer dishId) {
		return dishRepository.findById(dishId).orElse(null);
	}

	@Override
	public Boolean deleteDishById(Integer dishId) {
		try {
			dishRepository.deleteById(dishId);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
}
