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
@Table(name = "ruolo")
public class Ruolo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer idruolo;
	
	
	@NotNull    
	public String ruoli;
	
	@OneToMany(mappedBy = "ruolo", fetch = FetchType.EAGER)
	private Set<User> ruolo;


	public Ruolo(Integer idruolo, @NotNull String ruoli) {
		super();
		this.idruolo = idruolo;
		this.ruoli = ruoli;
	}
	
	
	public Ruolo () {
		
	}
	public Integer getidruolo() {
		return idruolo;
	}

	public void setidruolo(Integer idruolo) {
		this.idruolo = idruolo;
	}

	public String getRuoli() {
		return ruoli;
	}

	public void setRuoli(String ruoli) {
		this.ruoli = ruoli;
	}
	
	
	
	
}
