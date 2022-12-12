package com.gruppo2.fullstack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gruppo2.fullstack.model.Insegnamento;

import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Utente;

import jakarta.transaction.Transactional;


@Repository
public interface InsegnamentoDao extends CrudRepository<Insegnamento, Integer>{


	
	
	
	@Query(value = "select modulo  from insegnamento \r\n"
			+ "inner join modulo on insegnamento.idmodulo = modulo.idmodulo\r\n"
			+ "where idutente= :x",nativeQuery=true)

	public String Materia(Integer x);
	
	Insegnamento findByIdinsegnamento(Integer idinsegnamento);
	Insegnamento findByUtente(Utente utente);
	
	@Query(value = "SELECT * FROM `insegnamento` WHERE idutente = :idutente", nativeQuery = true)
	public Insegnamento singoloInsegnamento(Integer idutente);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM `insegnamento` WHERE `insegnamento`.`idutente` = :idutente", nativeQuery = true)
	public void rimuoviInsegnamento(Integer idutente);
	
}
