package com.gruppo2.fullstack.Dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Modulo;



@Repository
public interface ModuloDao extends CrudRepository<Modulo, Integer> {

	//List<Modulo> findByidmodulo(Integer idmodulo);
	//List<Modulo> findBymodulo(String modulo);
}
