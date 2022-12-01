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
	public String ruolo;
	
	@OneToMany(mappedBy = "ruolo", fetch = FetchType.EAGER)
	private Set<Utente> ruoloUtente;


	public Ruolo(Integer idruolo, @NotNull String ruolo) {
		super();
		this.idruolo = idruolo;
		this.ruolo = ruolo;
	}
	
	
	public Ruolo () {
		
	}
	public Integer getidruolo() {
		return idruolo;
	}

	public void setidruolo(Integer idruolo) {
		this.idruolo = idruolo;
	}

	public String getruolo() {
		return ruolo;
	}

	public void setruolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
	
	
}
