package com.example.sbmt.exceptions;

import com.example.sbmt.model.Type;

public class TransactionNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public TransactionNotFoundException(Integer id) {
		super("Could not find Transaction " + id);
	}
	
	public TransactionNotFoundException(Type type) {
		super("Could not find Transaction " + type);
	}
	
	public TransactionNotFoundException(String actor) {
		super("Could not find Transaction " + actor);
	}
}
