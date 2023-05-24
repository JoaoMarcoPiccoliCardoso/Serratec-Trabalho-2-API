package com.residencia.api1.exceptions;

public class UnmatchingIdsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UnmatchingIdsException(String message) {
		super(message);
	}
	
	public UnmatchingIdsException(Integer idPath, Integer id) {
		super("Valores declarados para os id do path e do objeto são diferentes");
	}
	
}
