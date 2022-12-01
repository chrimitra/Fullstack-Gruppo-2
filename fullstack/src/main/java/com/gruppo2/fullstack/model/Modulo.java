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
@Table(name = "modulo")
public class Modulo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer idmodulo;
	
	@NotNull    
	public String modulo;
	
	@OneToMany(mappedBy = "modulo", fetch = FetchType.EAGER)
	private Set<Feedback> moduloFeedback;
	
	
	public Modulo(Integer idmodulo, @NotNull String modulo) {
		super();
		this.idmodulo = idmodulo;
		this.modulo = modulo;
	}

	public Integer getIdmodulo() {
		return idmodulo;
	}

	public void setIdmodulo(Integer idmodulo) {
		this.idmodulo = idmodulo;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		return "Modulo [idmodulo=" + idmodulo + ", modulo=" + modulo + "]";
	}
	
	
}
