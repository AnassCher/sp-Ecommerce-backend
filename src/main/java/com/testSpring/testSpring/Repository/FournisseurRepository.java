package com.testSpring.testSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.testSpring.testSpring.Entities.Fournisseur;
@RepositoryRestResource
public interface FournisseurRepository  extends JpaRepository<Fournisseur, Long>{

}
