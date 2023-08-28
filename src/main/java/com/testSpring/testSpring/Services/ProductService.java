package com.testSpring.testSpring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testSpring.testSpring.Entities.Fournisseur;
import com.testSpring.testSpring.Entities.Product;
import com.testSpring.testSpring.Repository.FournisseurRepository;
import com.testSpring.testSpring.Repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository pR;
	
	@Autowired
	FournisseurRepository fR;
	
	public List<Product> getAll(){
		return pR.findAll();
	}
	
	public Optional<Product> getProduct(Long id) {
		return pR.findById(id);
	}
	
	public Product createProduct(Product p) {
		return pR.save(p);
	}
	
	public void updateProduct(Long id, Product p) {
		p.setId(id);
		pR.save(p);
		
	}
	
	public void delete(Long id) {
		pR.deleteById(id);
	}
	
	public Product AssignProductToFor(Long idF, Long idPr) {
		Product p = pR.findById(idPr).get();
		Fournisseur f = fR.findById(idF).get();
		
		p.setFournisseur(f);
		return pR.save(p);
	}

}
