package com.gruppo2.fullstack.Dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Ruoli;



@Repository
public interface RuoliDao extends CrudRepository<Ruoli, Integer> {
	



}
