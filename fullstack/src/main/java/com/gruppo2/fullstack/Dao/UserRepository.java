package com.gruppo2.fullstack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.User;



@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User>findByName(String name);
	List<User>findBySurname(String surname);
	List<User>findByEmail(String email);
	List<User>findByPassword(String password);



	@Query(value = "select *  from user  where email= :email and password = :password",nativeQuery=true)
		public User login(String email, String password) ;

	@Query(value = "select * from user where email= :email",nativeQuery=true)
		public User verificaMail(String email);

}



