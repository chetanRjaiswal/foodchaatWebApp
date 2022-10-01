package com.onlinefoodchaat.controller;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchaat.entity.Client;
import com.onlinefoodchaat.entity.Dish;
import com.onlinefoodchaat.entity.Notification;
import com.onlinefoodchaat.service.ClientService;
import com.onlinefoodchaat.service.DishService;
import com.onlinefoodchaat.service.EmailService;
import com.onlinefoodchaat.service.NotificationService;
import com.onlinefoodchaat.util.OtpUtil;

@Controller
public class ClientController {

    public static int otp;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private NotificationService notificationService;

	
	@PostMapping("/sendPaymentOtp")
	public ResponseEntity <String>sendPaymentOtp(@RequestParam String email)
	{		
		 try
		  {
			otp=OtpUtil.generateOtp();
		    //emailService.sendTextMail(email,"your OTP for suscription is "+otp, "OTP for Payment");
		    System.out.println(otp);
		    return ResponseEntity.status(HttpStatus.OK).body("ok");
		  }
		 catch(Exception e) 
		  { 
			 e.printStackTrace();
		  }
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
	}
	
	@SuppressWarnings("deprecation")
	@ResponseBody
	@PostMapping("/signUpclient")
	public ResponseEntity<?> clientSignUp(@RequestBody Client client )
	{
		int planMonth=0;
		
		Date date = new Date();
		
		if (client.getPlan().equals("one Month"))
			planMonth ++;
		
		else if (client.getPlan().equals("two Month"))
			planMonth += 2;
		
		else
			planMonth += 3;
		
		System.out.println("plan ="+planMonth);
		
		client.setBuyDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
		client.setExpireDate(new java.sql.Date(date.getYear(), date.getMonth() + planMonth, date.getDate()));
		
		if(client.getOtp()== otp && clientService.registerClient(client) )
		{
			 System.out.println("saved");
			 return ResponseEntity.status(HttpStatus.OK).body("ok"); 
		}
		else 
		{
			System.out.println("else");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
		}
	}
	
	
	@PostMapping("/clientLogin")
	public ModelAndView clientLogin(@RequestParam String email,@RequestParam String password,@RequestParam String otp,HttpServletRequest request)
	{
		
		ModelAndView modelView= new ModelAndView();
		Client clientObj=clientService.loginClient(email, password);
		
		HttpSession session=request.getSession();
		try
		{
		 if(Integer.parseInt(otp)==this.otp && clientObj!=null)
		 {
		  session.setAttribute("loginClientObj", clientObj);
		  session.setAttribute("loginClientId",clientObj.getId());
		  System.out.println("login come success");
		  modelView.setViewName("ClientDashBoard");
		 }
		 else {
			 modelView.setViewName("login");
		 }
		}
		catch (Exception e) 
		{
				session.setAttribute("error", "Login Failed");
		}
		return modelView;	
	}
	
	@GetMapping("/clientnotification")
	public ModelAndView getNotify(@RequestParam int clientId)
	{
		ModelAndView mav = new ModelAndView();
		List<Notification> nos = notificationService.getNotification(clientId);
		mav.addObject("clientNotifys", nos);
		mav.setViewName("clientNotification");
	    return mav;
	}
		
	@GetMapping("/clientLogout")
	public ModelAndView clientLogout(HttpServletRequest req)
	{
		req.getSession().removeAttribute("loginClientId");
		req.getSession().removeAttribute("loginClientObj");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@GetMapping("/getPlanDetails")
	public ModelAndView getPlanDetails(HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		int clientId = (Integer)req.getSession().getAttribute("loginClientId");
		req.getSession().removeAttribute("loginClientObj");
		String date = clientService.getPlanDetails(clientId); 
		
		mav.addObject("purchaseDate", date);
		mav.setViewName("clientAccountDetails");
		
		return mav;
	}

}
