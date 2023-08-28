package com.testSpring.testSpring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testSpring.testSpring.Entities.Client;
import com.testSpring.testSpring.Entities.Commande;
import com.testSpring.testSpring.Entities.Product;
import com.testSpring.testSpring.Repository.ClientRepository;
import com.testSpring.testSpring.Repository.CommandRepository;
import com.testSpring.testSpring.Repository.ProductRepository;

@Service
public class CommandeService {
	
	@Autowired
	CommandRepository cR;
	
	@Autowired
	ProductRepository pR;
	
	@Autowired
	ClientRepository clR;
	
	
	
	public List<Commande> getAll(){
		return cR.findAll();	
	}
	
	public Optional<Commande> getCommand(Long id){
		return cR.findById(id);
	}
	
	public Commande createCommand(Long clientId, List<Long> productIds, String status) {
        Client c = clR.findById(clientId).get();
        Commande com = new Commande();
        com.setClient(c);
        com.setDate(new Date());
        com.setStatus(status);
        com.setQuantity(productIds.size());
        //double price = 0;
        for(Long id : productIds) {
        	Product p = pR.findById(id).get();
        	//price += p.getPrice();
        	com.getProducts().add(p);
        }
        //com.setPriceTotale(price);
        return cR.save(com);
    }
	
	public Commande updateCommand(Long idCom, List<Long> list, Long clId, String status) {
		Commande commande = new Commande();
		commande.setId(idCom);
		commande.setDate(cR.findById(idCom).get().getDate());
		commande.setClient(clR.findById(clId).get());
		commande.setStatus(status);
		commande.setQuantity(list.size());
		List<Product> products = new ArrayList<>();
		//double price = 0;
		for(Long id : list) {
			Product p = pR.findById(id).get();
			//price += p.getPrice();
			products.add(p);
		}
		commande.setProducts(products);
		//commande.setPriceTotale(price);
		return cR.save(commande);
	}
	
	public void DeleteCom(Long id) {
		 cR.deleteById(id);
	}
	
	public double price(Long id) {
		Commande c = cR.findById(id).get();
		List<Product> list = c.getProducts();
		double priceTotal =0;
		double price =0;
		double tva;
		for(Product p : list) {
			tva = p.getTva() / 100;
			price = p.getPrice() + (p.getPrice() * tva);
			priceTotal +=price;
		}
		
		return priceTotal;
	}

}
