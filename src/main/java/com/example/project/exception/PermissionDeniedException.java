package com.example.project.exception;

public class PermissionDeniedException extends IllegalStateException {
	public PermissionDeniedException() {
		super();
	}

	public PermissionDeniedException(String s) {
		super(s);
	}
}
