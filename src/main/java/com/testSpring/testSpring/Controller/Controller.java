package com.testSpring.testSpring.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.testSpring.testSpring.Entities.Client;
import com.testSpring.testSpring.Entities.Commande;
import com.testSpring.testSpring.Entities.Fournisseur;
import com.testSpring.testSpring.Entities.Product;
import com.testSpring.testSpring.Services.ClientService;
import com.testSpring.testSpring.Services.CommandeService;
import com.testSpring.testSpring.Services.FournisseurService;
import com.testSpring.testSpring.Services.ProductService;

@RestController
@CrossOrigin("http://localhost:4200")
public class Controller {

	@Autowired
	ClientService cS;
	
	@Autowired
	CommandeService cmS;
	
	@Autowired
	ProductService pS;
	
	@Autowired
	FournisseurService fS;
	
	//Clients Functions --------------------------
	
	@PostMapping(path="/createC", produces = (MediaType.APPLICATION_JSON_VALUE))
	public Client createC(@RequestBody Client c) {
		return cS.createClient(c);
	}
	
	@GetMapping(path="/getAllC", produces= (MediaType.APPLICATION_JSON_VALUE))
	public List<Client> getAllC(){
		return cS.getAll();
	}
	@GetMapping(path="/getC/{id}", produces= (MediaType.APPLICATION_JSON_VALUE))
	public Optional<Client> getC(@PathVariable("id") Long id){
		return cS.getClient(id);
	}
	
	@DeleteMapping(path="/deleteC/{id}")
	public void deleteC(@PathVariable("id") Long id) {
		 cS.delete(id);
	}
	
	
	
	
	//Fournisseur Functions --------------
	
	@PostMapping(path="/createF", produces = (MediaType.APPLICATION_JSON_VALUE))
	public Fournisseur create(@RequestBody Fournisseur f) {
		return fS.createFournisseur(f);
	}
	
	@GetMapping(path="/getAllF", produces= (MediaType.APPLICATION_JSON_VALUE))
	public List<Fournisseur> getAllF(){
		return fS.getAll();
	}
	@GetMapping(path="/getF/{id}", produces= (MediaType.APPLICATION_JSON_VALUE))
	public Optional<Fournisseur> getF(@PathVariable("id") Long id){
		return fS.getFournisseur(id);
	}
	
	@GetMapping(path="/getAllPrOfFor/{id}", produces= (MediaType.APPLICATION_JSON_VALUE))
	public List<Product> getAllProfF(@PathVariable("id") Long id){
		return fS.Products(id);
	}
	
	@DeleteMapping(path="/deleteF/{id}")
	public void deleteF(@PathVariable("id") Long id) {
		 fS.delete(id);
	}
	
	
	
	
	//Product functions --------------------
	
	@GetMapping(path="/getAllP", produces= (MediaType.APPLICATION_JSON_VALUE))
	public List<Product> getAllP(){
		return pS.getAll();
	}
	@GetMapping(path="/getP/{id}", produces= (MediaType.APPLICATION_JSON_VALUE))
	public Optional<Product> getP(@PathVariable("id") Long id){
		return pS.getProduct(id);
	}
	
	@PostMapping(path="/createP", produces = (MediaType.APPLICATION_JSON_VALUE))
	public Product create(@RequestBody Product p) {
		return pS.createProduct(p);
	}
	
	@PutMapping(path="/{idF}/Product/{idP}", produces = (MediaType.APPLICATION_JSON_VALUE))
	public Product AssigneFourToPro(@PathVariable() Long idF, @PathVariable() Long idP) {
		return pS.AssignProductToFor(idF, idP);
	}
	
	@DeleteMapping(path="/deleteP/{id}")
	public void deleteP(@PathVariable("id") Long id) {
		 pS.delete(id);
	}
	
	
	//Commande Functions --------------------------
	
	
	@PostMapping(path="/createCom/{idCl}", produces = (MediaType.APPLICATION_JSON_VALUE))
	public Commande createCommande(@PathVariable Long idCl, @RequestBody List<Long> p, @RequestParam(name="status") String status ) {
		return cmS.createCommand(idCl, p, status);
	}
	
	
	@PutMapping(path="/updateCommande/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Commande updateCommande(@PathVariable("id") Long idCom, @RequestBody() List<Long> list,
			@RequestParam(required = false) Long idCl, 
			@RequestParam("Status") String status) {
		
		
		if(idCl == null) {
			idCl = cmS.getCommand(idCom).get().getClient().getId();
		}
		return cmS.updateCommand(idCom, list, idCl, status);
	}
	
	
	
	@GetMapping(path="/getAllCom", produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Commande> getAllCom(){
		return cmS.getAll();
	}
	
	@GetMapping(path="/getCom/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Commande getCom(@PathVariable("id") Long id) {
		return cmS.getCommand(id).get();
	}
	
	@DeleteMapping(path="/deleteCom/{id}")
	public void deleteCom(@PathVariable("id") Long id) {
		 cmS.DeleteCom(id);
	}
	
	
	//Price of commande
	@GetMapping(path="/price/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String priceCommande(@PathVariable("id") Long id) {
		double price = cmS.price(id);
		return price+" DH";
	}
	
	
	
	
	
}
