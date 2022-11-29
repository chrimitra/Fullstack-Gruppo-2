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
    public Integer id_user	;
	
	@NotNull
    @Size(min=4, max=45)
	public String name;
	
	@NotNull
    @Size(min=4, max=45)
	public String surname;
	
	@NotNull
    @Size(min=4, max=45)
	public String email;
	
	@NotNull
    @Size(min=4, max=45)
	public String password;
	
	@ManyToOne
	@JoinColumn(name="ruolo_id")
	private Ruoli ruolo;
	
	
	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
	private Set<Feedback> feedback;


	public User() {
		super();
		
	}





	public User(Integer id_user, @NotNull @Size(min = 4, max = 45) String name,
			@NotNull @Size(min = 4, max = 45) String surname, @NotNull @Size(min = 4, max = 45) String email,
			@NotNull @Size(min = 4, max = 45) String password) {
		super();
		this.id_user = id_user;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}





	public Integer getId_user() {
		return id_user;
	}


	public void setId_user(Integer id_user) {
		this.id_user = id_user;
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


	public Ruoli getRuolo() {
		return ruolo;
	}


	public void setRuolo(Ruoli ruolo) {
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
		return "User [id_user=" + id_user + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", password=" + password + ", ruolo=" + ruolo + ", feedback=" + feedback + "]";
	}	
	
	

	
	
	
}
