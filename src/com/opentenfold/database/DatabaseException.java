package com.opentenfold.database;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 3159767979372655331L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
