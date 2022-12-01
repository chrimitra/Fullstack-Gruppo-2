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
@Table(name = "user")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer iduser	;
	
	@NotNull
    @Size(min=4, max=45)
	public String name;
	
	@NotNull
    @Size(min=4, max=45)
	public String surname;
	
	@NotNull
    @Size(min=4, max=45)
	public String email;
	
    
	public String password;
	
	@ManyToOne
	@JoinColumn(name="idruolo")
	private Ruolo ruolo;
	
	
	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
	private Set<Feedback> feedback;


	public User() {
		super();
		
	}





	public User(Integer iduser, @NotNull @Size(min = 4, max = 45) String name,
			@NotNull @Size(min = 4, max = 45) String surname, @NotNull @Size(min = 4, max = 45) String email,
			@NotNull @Size(min = 4, max = 45) String password) {
		super();
		this.iduser = iduser;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}





	public Integer getiduser() {
		return iduser;
	}


	public void setiduser(Integer iduser) {
		this.iduser = iduser;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
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
		return "User [iduser=" + iduser + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", password=" + password + ", ruolo=" + ruolo + ", feedback=" + feedback + "]";
	}	
	
	

	
	
	
}
