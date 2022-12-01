package com.gruppo2.fullstack.model;

import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Utente")
public class Utente {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer idutente	;
	
	@NotNull
    @Size(min=4, max=45)
	public String nome;
	
	@NotNull
    @Size(min=4, max=45)
	public String cognome;
	
	@NotNull
    @Size(min=4, max=45)
	public String email;
	
    
	public String password;
	
	@ManyToOne
	@JoinColumn(name="idruolo")
	private Ruolo ruolo;
	
	
	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
	private Set<Feedback> feedback;


	public Utente() {
		super();
		
	}





	public Utente(Integer idutente, @NotNull @Size(min = 4, max = 45) String nome,
			@NotNull @Size(min = 4, max = 45) String cognome, @NotNull @Size(min = 4, max = 45) String email,
			@NotNull @Size(min = 4, max = 45) String password) {
		super();
		this.idutente = idutente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}





	public Integer getidutente() {
		return idutente;
	}


	public void setidutente(Integer idutente) {
		this.idutente = idutente;
	}


	public String getnome() {
		return nome;
	}


	public void setnome(String nome) {
		this.nome = nome;
	}


	public String getcognome() {
		return cognome;
	}


	public void setcognome(String cognome) {
		this.cognome = cognome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Ruolo getRuolo() {
		return ruolo;
	}


	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}


	public Set<Feedback> getFeedback() {
		return feedback;
	}


	public void setFeedback(Set<Feedback> feedback) {
		this.feedback = feedback;
	}


	@Override
	public String toString() {
		return "User [idutente=" + idutente + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", password=" + password + ", ruolo=" + ruolo + ", feedback=" + feedback + "]";
	}	
	
	

	
	
	
}
