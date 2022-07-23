package com.example.project.exception;

import java.util.ArrayList;
import java.util.List;

public class BadResourceException extends RuntimeException {
	private List<String> errorMessages = new ArrayList<>();

	public BadResourceException() {}

	public BadResourceException(String msg) {
		super(msg);
	}

	/**
	 * @return the errorMessages
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages the errorMessages to set
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public void addErrorMessage(String message) {
		this.errorMessages.add(message);
	}
}