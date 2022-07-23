package com.example.project.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
	public ResourceAlreadyExistsException() {}

	public ResourceAlreadyExistsException(String msg) {
		super(msg);
	}
}
