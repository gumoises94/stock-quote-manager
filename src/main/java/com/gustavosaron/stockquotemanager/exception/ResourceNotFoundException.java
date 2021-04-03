package com.gustavosaron.stockquotemanager.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536194045899043769L;

	
	public ResourceNotFoundException(Long id) {
		super(String.format("Could not find resource with id %d", id));
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
