package com.makemagic.school.exception;

public class NoContentException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoContentException() {
		super();
	}

	public NoContentException(String message) {
		super(message);
	}

	public NoContentException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoContentException(Throwable cause) {
		super(cause);
	}
}
