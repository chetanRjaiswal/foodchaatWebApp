package com.onlinefoodchaat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.onlinefoodchaat.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	@Query("select u from User u where u.email=:e and u.password=:p")
	public User findUser(@Param("e") String email,@Param("p") String password);
}
