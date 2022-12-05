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
	
	List <Feedback> findByIdfeedback (Integer idfeedback);
	Feedback findByvoto(double voto);
	List<Feedback>findBydata(String data);
	List<Feedback>findBydomanda(Domanda domanda);
	List<Feedback>findByutente(Utente utente);
	List<Feedback>findBymodulo(Modulo modulo);
	
	@Query(value = "SELECT * \r\n"
			+ "FROM feedback \r\n"
			+ "WHERE idmodulo= :idmodulo", nativeQuery = true)
	public List <Feedback> dettagli(Integer idmodulo);
}
