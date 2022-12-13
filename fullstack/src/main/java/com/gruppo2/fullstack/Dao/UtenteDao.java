package com.gruppo2.fullstack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Ruolo;
import com.gruppo2.fullstack.model.Utente;

import jakarta.transaction.Transactional;



@Repository
public interface UtenteDao extends CrudRepository<Utente, Integer> {
	
	Utente findByIdutente(Integer utente);
	List<Utente>findByNome(String nome);
	List<Utente>findByCognome(String cognome);
	List<Utente>findByEmail(String email);
	List<Utente>findByPassword(String password);
	Utente findByRuolo(Ruolo ruolo);
	
	// LOGIN
	@Query(value = "select *  from utente where email= :email and password = :password",nativeQuery=true)
	public Utente login(String email, String password) ;

	// VERIFICA MAIL
	@Query(value = "select * from utente where email= :email",nativeQuery=true)
	public Utente verificaMail(String email);
	
	// VERIFICA PASSWORD
	@Query(value = "select * from utente where password= :password",nativeQuery=true)
	public Utente verificaPassword(String password);
	
	// SELECT ALL
	@Query(value = "SELECT idutente, nome, cognome, email, password, idruolo FROM utente", nativeQuery = true)
	public List<Utente> listaUtente();
	
	// SELECT DI UN SINGOLO UTENTE
	@Query(value = "SELECT idutente, nome, cognome, email, password, idruolo FROM utente WHERE idutente = :idutente", nativeQuery = true)
	public Utente singoloUtente(Integer idutente);
		
	// EDIT DI UN SINGOLO UTENTE
	@Query(value = "UPDATE utente SET `password` = :password WHERE idutente = :idutente", nativeQuery = true)
	public Utente cambiaPassword(String password, Integer idutente);
	
	// BLOCCO UTENTE
	@Transactional
	@Modifying
	@Query(value = "UPDATE utente SET `password` = :password WHERE idutente = :idutente", nativeQuery = true)
	public void bloccoPassword(String password, Integer idutente);
	
	
	
}



