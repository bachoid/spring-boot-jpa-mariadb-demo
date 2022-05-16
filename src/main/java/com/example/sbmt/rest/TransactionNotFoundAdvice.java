package com.example.sbmt.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.sbmt.exceptions.TransactionNotFoundException;

@RestControllerAdvice
public class TransactionNotFoundAdvice {

	@ExceptionHandler(TransactionNotFoundException.class)
	String userNotFoundHandler(TransactionNotFoundException ex) {
		return ex.getMessage();
	}

}
