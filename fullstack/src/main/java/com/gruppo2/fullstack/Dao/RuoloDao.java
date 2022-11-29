package com.gruppo2.fullstack.Dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RuoloDao extends CrudRepository<RuoloDao, Long> {
	List<RuoloDao> findByIDruolo(int ruolo); 
	List<RuoloDao> findByruolo(String ruolo);
    RuoloDao findById(long id);
    
}
