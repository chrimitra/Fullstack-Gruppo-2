package com.gruppo2.fullstack.Dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Ruoli;



@Repository
public interface RuoliRepository extends CrudRepository<Ruoli, Integer> {
	



}
