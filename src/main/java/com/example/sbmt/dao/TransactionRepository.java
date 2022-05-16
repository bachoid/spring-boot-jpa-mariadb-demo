package com.example.sbmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.sbmt.model.Transaction;
import com.example.sbmt.model.Type;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
	
	Optional<List<Transaction>> findByType(Type type);
	
	Optional<List<Transaction>> findByActor(String actor);

}
