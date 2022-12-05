package com.gruppo2.fullstack.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "feedback")
public class Feedback {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer idfeedback;
	
	

	@NotNull  
	@Column(name = "voto")
	public double voto;
	
	
	@NotNull  
	public String data;
	
	@ManyToOne
	@JoinColumn(name="idutente")
	private Utente utente;
	
	
	@ManyToOne
	@JoinColumn(name="iddomanda")
	private Domanda domanda;
	
	@ManyToOne
	@JoinColumn(name="idmodulo")
	private Modulo modulo;
	
	
	public Feedback () {
		
	}

	public Feedback(Integer idfeedback, double voto, String data, Utente utente, Domanda domanda,
			Modulo modulo) {
		super();
		this.idfeedback = idfeedback;
		this.voto = voto;
		this.data = data;
		this.utente = utente;
		this.domanda = domanda;
		this.modulo = modulo;
	}

	public Integer getIdfeedback() {
		return idfeedback;
	}

	public void setIdfeedback(Integer idfeedback) {
		this.idfeedback = idfeedback;
	}

	public double getVoto() {
		return voto;
	}

	public void setVoto(double voto) {
		this.voto = voto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Domanda getDomanda() {
		return domanda;
	}

	public void setDomanda(Domanda domanda) {
		this.domanda = domanda;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}



	@Override
	public String toString() {
		return "Feedback [idfeedback=" + idfeedback + ", voto=" + voto + ", data=" + data + ", utente=" + utente
				+ ", domanda=" + domanda + ", modulo=" + modulo + "]";
	}
	
	

}
