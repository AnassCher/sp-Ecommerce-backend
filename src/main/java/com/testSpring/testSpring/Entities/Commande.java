package com.testSpring.testSpring.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int Quantity;
	private Date date;
	//private double priceTotale;
	private String status;
	
	@ManyToOne()
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client ;
	
	@ManyToMany()
	@JoinTable(name = "Command_produit", joinColumns = @JoinColumn(name="id_com"), inverseJoinColumns = @JoinColumn(name = "id_prod"))
	private List<Product> products = new ArrayList<>();
	
	
	
	

}
