package com.gruppo2.fullstack.Dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Ruolo;



@Repository
public interface RuoloDao extends CrudRepository<Ruolo, Integer> {
	
	List<Ruolo> findByidruolo(Integer idruolo);
	Ruolo findByruolo(String ruolo);

}
