package com.gruppo2.fullstack.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Modulo;



@Repository
public interface ModuloDao extends CrudRepository<Modulo, Integer> {

	Modulo findByIdmodulo(Integer idmodulo);
	List<Modulo> findBymodulo(String modulo);
}
