package com.gruppo2.fullstack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public class UtenteDao {

	public interface UserDao extends CrudRepository<UserDao, Long> {
		List<UserDao> findByNome(String name); 
		List<UserDao> findByCognome(String Cognome);
	    List<UserDao> findByEmail(String email); 
	    List<UserDao> findByPassword(String password);
	    UserDao findById(long id);
	    
	    @Query("select s from User s where username= :username and password = :password")
		public UserDao login(String username, String Cognome, String Email, String password);
	}
		//*//
}
