package com.onlinefoodchaat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchaat.entity.Client;
import com.onlinefoodchaat.entity.Dish;
import com.onlinefoodchaat.service.ClientService;
import com.onlinefoodchaat.service.DishService;

@RestController
public class RestoController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private DishService dishService;
	
	@GetMapping("/addResto")
	public ModelAndView pageRedirect(@RequestParam Integer clientId)
	{
		 ModelAndView mav= new ModelAndView();
		 Client client = clientService.getById((clientId));
		 if(client!=null && client.getRestoName()==null)
		 {
			 mav.setViewName("addResto");
			 return mav;
		 }
		 mav.setViewName("addDish");
		 return mav;
	}
	
	
	@PostMapping("/addRestoDetails")
	public ModelAndView addRestobyClient(@RequestParam String clinetId,@RequestParam String restoName,HttpServletRequest request)
	{
		Integer id= Integer.parseInt(clinetId);
		ModelAndView mav=new ModelAndView();
		HttpSession session = request.getSession();
		
		if( clientService.getById(id,restoName)) {
		 System.out.println("in the add resto success");
		 mav.setViewName("addDish");
		 return mav;
		}
		 session.setAttribute("restoErrMsg", "resto already added Please Add new Name");
		 mav.setViewName("addResto");
		 return mav;
	}
	
	@GetMapping("/getRestos")
	public ModelAndView getRestos()
	{
		ModelAndView mav = new ModelAndView();
		List<Client>clientList= clientService.getClients();
		mav.addObject("ListOfClients", clientList);
		mav.setViewName("userDashBoard"); 
		return mav;
	}
	
	@GetMapping("/addDish")
	public ModelAndView sendRedirect(@RequestParam Integer clientId)
	{
		ModelAndView mav= new ModelAndView();
		if( clientService.getById(clientId).getRestoName()==null)
		{
		 mav.setViewName("addResto");
		 return mav;
		}
		mav.setViewName("addDish");
		return mav;
	}
	
	@PostMapping("/addDishDetails")
	public ModelAndView addDish(@RequestParam("dishName")String dishName,@RequestParam("price")String price,@RequestParam("dishImg")MultipartFile file,HttpServletRequest req)
	{
		ModelAndView mav= new ModelAndView();
		Integer clientId=(Integer)req.getSession().getAttribute("loginClientId");
		 System.out.println(dishName+price+file+clientId);
		dishService.addDish(dishName, price, file,clientId);
		mav.setViewName("redirect:/viewAllDishesByClient");
	
		return mav;
	}
	
	@GetMapping("/viewAllDishesByClient")
	public ModelAndView viewDishByClient(HttpServletRequest req)
	{
		ModelAndView mav= new ModelAndView();
		Integer clientID=(Integer)req.getSession().getAttribute("loginClientId");
		System.out.println(clientID);
		List<Dish> listDishByClientId = dishService.getAllDishByClientId(clientID);
		mav.setViewName("clientAllDish");
		System.out.println(listDishByClientId);
		mav.addObject("ListOfDishes", listDishByClientId);
		return mav;
	}
	
	@GetMapping("/editDish")
	public ModelAndView editDishpage(HttpServletRequest req,@RequestParam("dishId")String dishId)
	{
		ModelAndView modelAndView=new ModelAndView();
		if((Integer)(req.getSession().getAttribute("loginClientId"))!=null)
		{
			Dish dish=dishService.getDishById(Integer.parseInt(dishId));
			req.getSession().setAttribute("dishId",dishId);
			System.out.println("dish------------->"+dish);
			modelAndView.addObject("dishById", dish);
			modelAndView.setViewName("editDishes");
			return modelAndView;
		}
		modelAndView.setViewName("ClientDashBoard");
		return modelAndView;
	}
	
	@PostMapping("/editDishDetails")
	public ModelAndView editDishDetails(@ModelAttribute Dish dish,@RequestParam("dishimg")MultipartFile file)
	{
		//@RequestParam("dishName")String dishName,@RequestParam("price")String price,@RequestParam("dishImg")MultipartFile file
		ModelAndView mav =  new ModelAndView();
	    try{
	    	Integer dishId=dish.getId();
	    	String dishName=dish.getDishName();
	    	String price=dish.getDishPrice();
			System.out.println("editdishdeatilas----->"+dishId+" "+dishName+" --"+price+"--"+file);
			if(dishService.editDish(dishId, dishName, price, file))
			{
				mav.setViewName("redirect:/viewAllDishesByClient");
				return mav;
			}
			
		   } catch (Exception e) 
	        {
			   e.getMessage();
		    }
		mav.setViewName("editDishes");
		return mav;
	}
	
	@GetMapping("/deleteDish")
	public ModelAndView deleteDish(@RequestParam("dishId")Integer dishId)
	{
		ModelAndView mav = new ModelAndView();
		//System.out.println("dishId"+dishId);
		if(dishService.deleteDishById(dishId))
		{
			mav.setViewName("redirect:/viewAllDishesByClient");
		}
		return mav;
	}
	
}
