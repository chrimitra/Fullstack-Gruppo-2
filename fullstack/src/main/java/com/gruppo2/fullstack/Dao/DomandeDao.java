package com.gruppo2.fullstack.Dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.gruppo2.fullstack.Dao.UtenteDao.UserDao;


public interface DomandeDao extends CrudRepository<DomandeDao, Long> {
		List<DomandeDao> findByIDdomande(int domande); 
		List<DomandeDao> findByDomande(String Domande);
	    UserDao findById(long id);
	    

}
