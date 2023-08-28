package com.testSpring.testSpring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import com.testSpring.testSpring.Entities.Fournisseur;
import com.testSpring.testSpring.Entities.Product;
import com.testSpring.testSpring.Repository.FournisseurRepository;
import com.testSpring.testSpring.Repository.ProductRepository;

@Service
public class FournisseurService {
	
	@Autowired
	FournisseurRepository fR;
	@Autowired
	ProductRepository pR;
	
	@Query("SELECT f FROM Fournisseur f JOIN FETCH f.products")
	public List<Fournisseur> getAll(){
		return fR.findAll();
	}
	
	public Optional<Fournisseur> getFournisseur(Long id) {
		return fR.findById(id);
	}
	
	public Fournisseur createFournisseur(Fournisseur f) {
		return fR.save(f);
	}
	
	public void updateFournisseur(Long id, Fournisseur f) {
		f.setId(id);
		fR.save(f);
		
	}
	
	public void delete(Long id) {
		fR.deleteById(id);
	}
	
	public List<Product> Products(Long id){
		return fR.findById(id).get().getProductsF();
	}
	
	

}
