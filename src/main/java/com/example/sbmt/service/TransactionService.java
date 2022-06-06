package com.example.sbmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbmt.dao.TransactionDataRepository;
import com.example.sbmt.dao.TransactionRepository;
import com.example.sbmt.model.Transaction;
import com.example.sbmt.model.TransactionData;
import com.example.sbmt.model.Type;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionDataRepository transactionDataRepository;

	public java.util.Optional<List<Transaction>> findByType(Type type) {
		return this.transactionRepository.findByType(type);
	}

	public java.util.Optional<List<Transaction>> findByActor(String actor) {
		return this.transactionRepository.findByActor(actor);
	}

	public List<Transaction> findAll() {
		List<Transaction> transactions = new ArrayList<>();
		this.transactionRepository.findAll().forEach(t -> transactions.add(t));
		return transactions;
	}

	@Transactional
	public Transaction save(Transaction transaction) {
		List<TransactionData> tdl = transaction.getTransactionData();
		transaction.setTransactionData(new ArrayList<>());
		final Transaction tSaved = transactionRepository.save(transaction);
		tdl.forEach(td -> {
			td.setTransaction(tSaved);
		});
		tSaved.getTransactionData().addAll(tdl);
		Transaction tSaved2 = transactionRepository.save(tSaved);
		return tSaved2;
	}

	public Optional<Transaction> findById(Integer id) {
		return this.transactionRepository.findById(id);
	}

	public void deleteById(Integer id) {
		this.transactionRepository.deleteById(id);
	}
}
