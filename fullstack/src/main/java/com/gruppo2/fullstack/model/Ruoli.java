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
public class Ruoli {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id_ruolo;
	
	
	@NotNull    
	public String ruoli;
	
	@OneToMany(mappedBy = "ruolo", fetch = FetchType.EAGER)
	private Set<User> ruolo;

	public Ruoli(Integer id_ruolo, @NotNull String ruoli) {
		super();
		this.id_ruolo = id_ruolo;
		this.ruoli = ruoli;
	}

	public Integer getId_ruolo() {
		return id_ruolo;
	}

	public void setId_ruolo(Integer id_ruolo) {
		this.id_ruolo = id_ruolo;
	}

	public String getRuoli() {
		return ruoli;
	}

	public void setRuoli(String ruoli) {
		this.ruoli = ruoli;
	}
	
	
	
	
}
