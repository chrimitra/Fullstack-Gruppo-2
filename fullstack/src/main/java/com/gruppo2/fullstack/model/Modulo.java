package com.gruppo2.fullstack.model;

import java.util.Set;




import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

	 @OneToOne	
	 @JoinColumn(name = "insegnante", referencedColumnName = "idutente" )
	 public Utente utente;
	
	@OneToMany(mappedBy = "modulo", fetch = FetchType.EAGER)
	private Set<Feedback> moduloFeedback;

	@Override
	public String toString() {
		return "Modulo [idmodulo=" + idmodulo + ", modulo=" + modulo + ", utente=" + utente + "]";
	}


	
	

	
	
	
}
