package com.academy.exceptions;

public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5629101068523010934L;

	public ModelNotFoundException(String message) {
		super(message);
	}
}
