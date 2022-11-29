package com.gruppo2.fullstack.Dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;



public interface DomandeDao extends CrudRepository<DomandeDao, Long> {
		List<DomandeDao> findByIDdomande(int domande); 
		List<DomandeDao> findByDomande(String Domande);
	   DomandeDao findById(long id);
	    

}
