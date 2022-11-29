package com.gruppo2.fullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	public User(Integer idUser) {
		super();
		this.id_user	 = idUser;
	}

	public User() {
		super();
	}

	public Integer getIdUser() {
		return id_user	;
	}

	public void setIdUser(Integer idUser) {
		this.id_user = idUser;
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
	
	
}
