package com.gruppo2.fullstack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Domanda;
import com.gruppo2.fullstack.model.Feedback;
import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Utente;
import com.gruppo2.fullstack.risorse.Feedbacks;

import jakarta.transaction.Transactional;

@Repository
public interface FeedbackDao extends CrudRepository<Feedback, Integer> {

	List <Feedback> findByIdfeedback (Integer idfeedback);
	Feedback findByvoto(double voto);
	List<Feedback>findBydata(String data);
	Feedback findBydomanda(Domanda domanda);
	List<Feedback>findByutente(Utente utente);
	List<Feedback>findBymodulo(Modulo modulo);
	
	// FEEDBACK PER MODULO
	@Query(value = "SELECT * \r\n"
			+ "FROM feedback \r\n"
			+ "WHERE idmodulo= :idmodulo", nativeQuery = true)
	public List <Feedback> dettagli(Integer idmodulo);
	
	// AGGIUNTA FEEDBACK
	@Modifying
	@Transactional
	@Query(value="INSERT INTO `feedbacks` (`idfeedback`, `voto`, `data`, `idutente`, `iddomanda`, `idmodulo`) "
			+ "VALUES (NULL, :voto, current_timestamp(), :idutente, :iddomanda, :idmodulo);",nativeQuery = true)
	public void aggiuntaFeedback(double voto, Integer idutente, Integer iddomanda, Integer idmodulo);
	
	// NORMALE
	@Query(value="SELECT * FROM `feedback`",nativeQuery = true)
	public List <Feedback> feedback();
	
	// FILTRA PER DATA
	@Query(value="SELECT * FROM `feedback` ORDER BY data",nativeQuery = true)
	public List <Feedback> ordineData();
	
	// FILTRA PER VOTO
	@Query(value = "SELECT * FROM `feedback` ORDER BY voto DESC", nativeQuery = true)
	public List <Feedback> ordineVoto();
	
	// FILTRA PER DOMANDA
	@Query(value="SELECT * FROM `feedback` ORDER BY iddomanda ASC", nativeQuery = true)
	public List <Feedback> ordineDomanda();
	
	// VISUALIZZARE VOTI 
	@Query(value=" SELECT voto FROM feedback \r\n"
            +"INNER JOIN modulo ON modulo.idmodulo = feedback.idmodulo \r\n"
            + "WHERE modulo = :modulo && iddomanda = :iddomanda ", nativeQuery = true)
    public List<Double> Voti(String modulo , Integer iddomanda);
}
