package com.gruppo2.fullstack.Dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Domanda;



@Repository
public interface DomandaDao extends CrudRepository<Domanda, Integer> {
	
}



