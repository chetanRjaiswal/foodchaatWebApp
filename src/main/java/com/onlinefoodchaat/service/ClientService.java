package com.onlinefoodchaat.service;
import java.util.Date;
import java.util.List;

import com.onlinefoodchaat.entity.Client;


public interface ClientService {

	public boolean registerClient(Client client);
	public Client loginClient(String email,String password);
	public Boolean getById(Integer id,String restoName);
	public Client getById(Integer id);
	public List<Client> getClients();
	public List<Client> searchByRestoName(String restoName);
	public String getPlanDetails(int clientId);
}
