package com.gruppo2.fullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "insegnamento")
public class Insegnamento {
	
	@ManyToOne
	@JoinColumn(name="idmodulo")
	private Modulo modulo;
	
	
	@ManyToOne
	@JoinColumn(name="idutente")
	private Utente utente;


	public Insegnamento(Modulo modulo, Utente utente) {
		super();
		this.modulo = modulo;
		this.utente = utente;
	}


	public Insegnamento() {
		super();
	
	}


	@Override
	public String toString() {
		return "Insegnamento [modulo=" + modulo + ", utente=" + utente + "]";
	}


	public Modulo getModulo() {
		return modulo;
	}


	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}


	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	

}
