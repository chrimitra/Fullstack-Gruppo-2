package com.gruppo2.fullstack.Dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Domanda;
import com.gruppo2.fullstack.model.Feedback;
import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Utente;



@Repository
public interface FeedbackDao extends CrudRepository<Feedback, Integer> {
	
	Feedback findByidfeedback (Integer idfeedback);
	//List<Feedback>findByvoto(Integer voto);
	//List<Feedback>findBydata(String data);
	//List<Feedback>findBydomanda(Domanda domanda);
	//List<Feedback>findByutente(Utente utente);
	//List<Feedback>findBymodulo(Modulo modulo);
	
	@Query(value = "SELECT domanda.domanda, AVG(voto) \r\n"
			+ "FROM `feedback`\r\n"
			+ "INNER JOIN domanda ON domanda.iddomanda = feedback.iddomanda\r\n"
			+ "WHERE idmodulo = :idmodulo\r\n"
			+ "GROUP BY domanda;", nativeQuery = true)
	public Feedback dettagli(Integer idmodulo);
}
