package com.residencia.api1.exceptions;

public class UniqueElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UniqueElementException(String message) {
		super(message);
	}
	
	public UniqueElementException() {
		super("Instrutor já tem telefone cadastrado.");
	}
	
	public UniqueElementException(String elemento, String valor) {
		super("Valor " + valor + " de " + elemento + " já existe.");
	}

	
}