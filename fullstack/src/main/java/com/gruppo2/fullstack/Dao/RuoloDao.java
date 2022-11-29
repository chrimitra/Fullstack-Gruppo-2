package com.gruppo2.fullstack.Dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.gruppo2.fullstack.Dao.UtenteDao.UserDao;

public interface RuoloDao extends CrudRepository<RuoloDao, Long> {
	List<RuoloDao> findByIDruolo(int ruolo); 
	List<RuoloDao> findByruolo(String ruolo);
    UserDao findById(long id);
    
}
