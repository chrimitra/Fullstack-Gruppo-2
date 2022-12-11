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

	

	
	  List <Feedback> findByIdfeedback (Integer idfeedback); Feedback
	  findByvoto(double voto); List<Feedback>findBydata(String data); Feedback
	  findBydomanda(Domanda domanda); List<Feedback>findByutente(Utente utente);
	  List<Feedback>findBymodulo(Modulo modulo);
	  
	  @Query(value = "SELECT * \r\n" + "FROM feedback \r\n" +
	  "WHERE idmodulo= :idmodulo", nativeQuery = true) public List <Feedback>
	  dettagli(Integer idmodulo);
	  
	  
	  
	  
		/*
		 * @Query(value =
		 * "SELECT idfeedback, data, domanda.iddomanda, idmodulo, idutente, voto, AVG(voto) \r\n"
		 * + "FROM `feedback` \r\n" +
		 * "INNER JOIN domanda ON domanda.iddomanda = feedback.iddomanda\r\n" +
		 * "WHERE idmodulo = :idmodulo \r\n" + "GROUP BY domanda", nativeQuery = true)
		 * public List <Feedback> media(Integer idmodulo);
		 * 
		 * // Me li ragruppa in domanda ma non mi prende la media
		 */	  
	  
	  
	  
	  //aggiunta feedback
	  
	  @Modifying
	  
	  @Transactional
	  
	  @Query(
	  value="INSERT INTO `feedbacks` (`idfeedback`, `voto`, `data`, `idutente`, `iddomanda`, `idmodulo`) "
	  +
	  "VALUES (NULL, :voto, current_timestamp(), :idutente, :iddomanda, :idmodulo);"
	  ,nativeQuery = true) public void aggiuntaFeedback(double voto, Integer
	  idutente, Integer iddomanda, Integer idmodulo);
	 

}
