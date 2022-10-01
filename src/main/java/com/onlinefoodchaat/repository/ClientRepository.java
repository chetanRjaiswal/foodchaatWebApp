package com.onlinefoodchaat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinefoodchaat.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	@Query("select u from Client u where u.email=:e and u.password=:p")
	public Client findClient(@Param("e") String email,@Param("p") String password);
	
	@Query("select e from Client e where e.restoName like %:name%")
	List<Client> findByRestoNameContaining(@Param("name") String name); // fetch list of resto containing name
	
}
