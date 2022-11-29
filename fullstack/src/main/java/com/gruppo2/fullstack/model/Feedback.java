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
	

	
	

}
