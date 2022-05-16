package com.example.sbmt.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.sbmt.model.TransactionData;

public interface TransactionDataRepository extends CrudRepository<TransactionData, Integer> {

}
