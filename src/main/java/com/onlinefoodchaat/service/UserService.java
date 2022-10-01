package com.onlinefoodchaat.service;

import com.onlinefoodchaat.entity.User;


public interface UserService {

	public Boolean registerUser(User user);
	public User loginUser(String email,String password);
}
  