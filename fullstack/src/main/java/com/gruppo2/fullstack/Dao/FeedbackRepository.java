package com.gruppo2.fullstack.Dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Domande;


@Repository
public interface FeedbackRepository extends CrudRepository<Domande, Integer> {
	


}
