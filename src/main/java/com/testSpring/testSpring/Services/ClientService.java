package com.testSpring.testSpring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testSpring.testSpring.Entities.Client;
import com.testSpring.testSpring.Repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository cR;
	
	
	public List<Client> getAll(){
		return cR.findAll();
	}
	
	public Optional<Client> getClient(Long id) {
		return cR.findById(id);
	}
	
	public Client createClient(Client c) {
		return cR.save(c);
	}
	
	public void updateClient(Long id, Client c) {
		c.setId(id);
		cR.save(c);
		
	}
	
	public void delete(Long id) {
		cR.deleteById(id);
	}
	
	
}
