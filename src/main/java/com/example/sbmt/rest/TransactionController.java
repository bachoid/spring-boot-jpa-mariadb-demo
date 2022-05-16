package com.example.sbmt.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbmt.exceptions.TransactionNotFoundException;
import com.example.sbmt.model.Transaction;
import com.example.sbmt.model.Type;
import com.example.sbmt.service.TransactionService;

@RestController
public class TransactionController {
	
	private TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping("/Transactions")
	List<Transaction> all() {
		return transactionService.findAll();
	}

	@PostMapping("/Transactions")
	Transaction newTransaction(@RequestBody Transaction transaction) {
	   System.out.println(transaction);
		return this.transactionService.save(transaction);
	}

	@GetMapping("/Transactions/id/{id}")
	Transaction byId(@PathVariable Integer id) {
		return this.transactionService.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
	}

	@GetMapping("/Transactions/type/{type}")
	List<Transaction> byType(@PathVariable Type type) {
		return this.transactionService.findByType(type).orElse(Collections.emptyList());
	}
	
	@GetMapping("/Transactions/actor/{actor}")
	List<Transaction> byActor(@PathVariable String actor) {
		return this.transactionService.findByActor(actor).orElse(Collections.emptyList());
	}
	
	@PutMapping("/Transactions/id/{id}")
	Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable Integer id) {
		return this.transactionService.findById(id).map(t -> {
			t.setType(transaction.getType());
			t.setActor(transaction.getActor());
			t.setTransactionData(transaction.getTransactionData());
			return this.transactionService.save(t);
		}).orElseGet(() -> {
			return this.transactionService.save(transaction);
		});
	}

	@DeleteMapping("/Transactions/id/{id}")
	void deleteUser(@PathVariable Integer id) {
		this.transactionService.deleteById(id);
	}

}
