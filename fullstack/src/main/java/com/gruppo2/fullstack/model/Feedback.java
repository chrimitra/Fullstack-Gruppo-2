package com.gruppo2.fullstack.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "feedbacks")
public class Feedback {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id_feedback;
	
	@NotNull  
	public Integer voto;
	
	
	@NotNull  
	public Date data;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User utente;
	
	
	@ManyToOne
	@JoinColumn(name="domande_id")
	private Domande domanda;


	public Feedback(Integer id_feedback, @NotNull Integer voto, @NotNull Date data, User utente, Domande domanda) {
		super();
		this.id_feedback = id_feedback;
		this.voto = voto;
		this.data = data;
		this.utente = utente;
		this.domanda = domanda;
	}


	public Integer getId_feedback() {
		return id_feedback;
	}


	public void setId_feedback(Integer id_feedback) {
		this.id_feedback = id_feedback;
	}


	public Integer getVoto() {
		return voto;
	}


	public void setVoto(Integer voto) {
		this.voto = voto;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public User getUtente() {
		return utente;
	}


	public void setUtente(User utente) {
		this.utente = utente;
	}


	public Domande getDomanda() {
		return domanda;
	}


	public void setDomanda(Domande domanda) {
		this.domanda = domanda;
	}
	

	
	

}
