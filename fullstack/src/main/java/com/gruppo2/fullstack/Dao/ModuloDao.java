package com.gruppo2.fullstack.Dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Utente;



@Repository
public interface ModuloDao extends CrudRepository<Modulo, Integer> {


	//Modulo findByIdmodulo(Integer idmodulo);
	//List<Modulo> findBymodulo(String modulo);
	Modulo findByIdmodulo(Integer idmodulo);
	Modulo findBymodulo(String modulo);
	List<Modulo> findByUtente(Utente insegnante);
	

}
