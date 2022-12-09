package com.gruppo2.fullstack.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Insegnamento;



@Repository
public interface InsegnamentoDao extends CrudRepository<Insegnamento, Integer>{


	
	
	
	@Query(value = "select modulo  from insegnamento \r\n"
			+ "inner join modulo on insegnamento.idmodulo = modulo.idmodulo\r\n"
			+ "where idutente= :x",nativeQuery=true)
	public String Materia(Integer x) ;

	Insegnamento findByIdinsegnamento(Integer idinsegnamento);
	
}
