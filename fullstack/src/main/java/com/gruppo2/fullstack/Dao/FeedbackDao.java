package com.gruppo2.fullstack.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


	public interface FeedbackDao extends CrudRepository<FeedbackDao, Long> {
		List<FeedbackDao> findByIDfeedback(int id);
		List<FeedbackDao> findByIDuser(int user);
	    List<FeedbackDao> findByIddomande(int id); 
	    List<FeedbackDao> findByVoto(int voto);
	    List<FeedbackDao> findByData(Date data);
	    FeedbackDao findById(long id);
	    

}

