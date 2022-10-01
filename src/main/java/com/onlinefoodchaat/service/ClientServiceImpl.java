package com.onlinefoodchaat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchaat.entity.Client;
import com.onlinefoodchaat.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public boolean registerClient(Client client) {
		
		try {
			clientRepository.save(client);
			System.out.println("clientservice impl");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
		return false;
	}

	@Override
	public Client loginClient(String email, String password) {
		Client client=null;
		try {
			 client=clientRepository.findClient(email, password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return client;
	}

	
	@Override
	public Client getById(Integer id) 
	{
		Client client=null;
		try 
		{
		  client=clientRepository.findById(id).orElse(null); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	
	
	@Override
	public  Boolean getById(Integer id,String restoName) 
	{
		Boolean f=false;
		Client client=null;
		try 
		{
		  client=clientRepository.findById(id).orElse(null);
		  client.setRestoName(restoName);
		  System.out.println(client+" ---"+restoName);
		  clientRepository.save(client); 
		  f=true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<Client> getClients() 
	{
		try {
			return clientRepository.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Client> searchByRestoName(String restoName) 
	{
	     try {
			List<Client> clients=clientRepository.findByRestoNameContaining(restoName);
			return clients;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


	public String getPlanDetails(int clientId) {
		try
		{
		  Client client = clientRepository.findById(clientId).orElse(null);
		  Date Purchase_Date = client.getBuyDate();
		  Date Expire_date=client.getExpireDate();
		  System.out.println(Purchase_Date);
		  return Purchase_Date+" --- "+Expire_date; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
