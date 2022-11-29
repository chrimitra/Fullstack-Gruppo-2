package com.gruppo2.fullstack.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;




@Entity
public class Domande {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id_domanda;
	
	
	@NotNull    
	public String domanda;
	
	@OneToMany(mappedBy = "domanda", fetch = FetchType.EAGER)
	private Set<Feedback> feedback;	
	
	
	

}


