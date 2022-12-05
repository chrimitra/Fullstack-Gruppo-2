package com.gruppo2.fullstack.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;




@Entity
@Table(name = "domanda")
public class Domanda {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer iddomanda;
	
	
	@NotNull    
	public String domanda;
	
	@OneToMany(mappedBy = "domanda", fetch = FetchType.EAGER)
	private Set<Feedback> feedback;

	

	public Domanda() {
		
	}
	public Domanda(Integer iddomanda, @NotNull String domanda) {
		super();
		this.iddomanda = iddomanda;
		this.domanda = domanda;
		
	}

	public Integer getiddomanda() {
		return iddomanda;
	}

	public void setiddomanda(Integer iddomanda) {
		this.iddomanda = iddomanda;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	
	
	

}


