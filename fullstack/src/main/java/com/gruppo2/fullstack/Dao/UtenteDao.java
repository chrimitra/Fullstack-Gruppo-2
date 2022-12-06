package com.gruppo2.fullstack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Utente;



@Repository
public interface UtenteDao extends CrudRepository<Utente, Integer> {
	
	Utente findByIdutente(Integer utente);
	List<Utente>findByNome(String nome);
	List<Utente>findByCognome(String cognome);
	List<Utente>findByEmail(String email);
	List<Utente>findByPassword(String password);

	

	@Query(value = "select *  from utente where email= :email and password = :password",nativeQuery=true)
		public Utente login(String email, String password) ;

	@Query(value = "select * from utente where email= :email",nativeQuery=true)
		public Utente verificaMail(String email);

}



