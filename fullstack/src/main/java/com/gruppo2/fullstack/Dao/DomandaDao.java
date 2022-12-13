package com.gruppo2.fullstack.Dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Domanda;



@Repository
public interface DomandaDao extends CrudRepository<Domanda, Integer> {

	Domanda findByiddomanda(Integer iddomanda);
	Domanda findBydomanda(String domanda);
	
	// SELECT ALL TUTTE LE DOMANDE
	@Query(value ="SELECT * FROM domanda" , nativeQuery = true)
    public List <Domanda> listadomande();
}



