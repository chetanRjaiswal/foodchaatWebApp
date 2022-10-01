package com.onlinefoodchaat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchaat.entity.User;
import com.onlinefoodchaat.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Boolean registerUser(User user) 
	{
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
		   System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public User loginUser(String email, String password) {
		try {
			User user=userRepository.findUser(email, password);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
 