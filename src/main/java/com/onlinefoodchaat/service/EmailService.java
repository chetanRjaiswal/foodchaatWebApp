package com.onlinefoodchaat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendTextMail(String toEmail,String body,String subject)
	{
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setFrom("chetanrjaiswal77@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		System.out.println("mail send....");
	}

}
