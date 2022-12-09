package com.gruppo2.fullstack.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gruppo2.fullstack.model.Modulo;

@Repository
public interface InsegnamentoDao extends CrudRepository<Modulo, Integer>{

}
