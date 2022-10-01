    package com.onlinefoodchaat.controller;
import com.onlinefoodchaat.entity.Client;
import com.onlinefoodchaat.entity.Dish;
import com.onlinefoodchaat.entity.User;
import com.onlinefoodchaat.service.ClientService;
import com.onlinefoodchaat.service.DishService;
import com.onlinefoodchaat.service.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

	@RestController
	public class UserController 
	{

		@Autowired
		private UserService userService;
		
		@Autowired
		private ClientService clientService;
		
		@Autowired
		private DishService dishService;
		
		@PostMapping("/userSignUp")
		public ResponseEntity<String> clientSignUp(@RequestBody User user )
		{
			System.out.println(CaptchaController.sImageCode);
			if(user.getCaptcha().equals(CaptchaController.sImageCode) && userService.registerUser(user))
			{
				 return ResponseEntity.status(HttpStatus.OK).body("ok"); 
			}
			else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
			}
		
		}
		
		@PostMapping("/userLogin")
		public ModelAndView clientLogin(@RequestParam String email,@RequestParam String password,@RequestParam String otp,HttpServletRequest request)
		{
			
			ModelAndView modelView= new ModelAndView();
			User user=userService.loginUser(email, password);
			
			HttpSession session=request.getSession();
			try
			{
			 if(/*Integer.parseInt(otp)==ClientController.otp &&*/ user!=null)
			 {
			  session.setAttribute("loginUserId",user.getId());
			  session.setAttribute("loginUserObj", user);
			  System.out.println("login user success");
			  //modelView.setViewName("userDashBoard");
			  modelView.setViewName("redirect:/getRestos");
			 }
			 else 
			 {
				 modelView.setViewName("login");
			 }
			}
			catch (Exception e) 
			{
					session.setAttribute("error", "Login Failed");
			}
			return modelView;	
		}
		
		
		@GetMapping("/userLogout")
		public ModelAndView userLogout(HttpServletRequest req) {
			ModelAndView mav=new ModelAndView();
			HttpSession session = req.getSession();
			session.removeAttribute("loginUserId");
			session.removeAttribute("loginUserObj");
			mav.setViewName("userLogin");
			return mav;
		}
		
		
		@GetMapping("/viewRestoMenu")
		public ModelAndView getRestoMenu(@RequestParam("getMenuByclientId")Integer clientId)
		{
			ModelAndView mav = new ModelAndView();
			List<Dish> listDishByClientId = dishService.getAllDishByClientId(clientId);
			mav.addObject("MenuList", listDishByClientId);
			//System.out.println(listDishByClientId);
			mav.setViewName("viewMenu");
			return mav;	
		}

		@PostMapping("/searchRestoController")
		public ModelAndView searchRestoByName(@RequestParam("restoName")String restoName)
		{
			System.out.println(restoName);
			ModelAndView mav = new ModelAndView();
			List<Client>clientsByResto= clientService.searchByRestoName(restoName);
			mav.addObject("clientsByRestoName",clientsByResto);
			mav.setViewName("searchResto");
			System.out.println(clientsByResto);
			return mav;
		}
		
		
	}
