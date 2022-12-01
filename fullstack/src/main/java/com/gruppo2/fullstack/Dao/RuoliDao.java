package com.gruppo2.fullstack.Dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Ruoli;



@Repository
public interface RuoliDao extends CrudRepository<Ruoli, Integer> {
	
	List<Ruoli> findByidruolo(Integer idruolo);
	List<Ruoli> findByruoli(String ruolo);

}
