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

	public Domande(Integer id_domanda, @NotNull String domanda, Set<Feedback> feedback) {
		super();
		this.id_domanda = id_domanda;
		this.domanda = domanda;
		this.feedback = feedback;
	}

	public Integer getId_domanda() {
		return id_domanda;
	}

	public void setId_domanda(Integer id_domanda) {
		this.id_domanda = id_domanda;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public Set<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(Set<Feedback> feedback) {
		this.feedback = feedback;
	}	
	
	
	

}


