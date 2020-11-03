package com.capgemini.Service.DBService;

//this class holds custom exceptions for sql
public class AddressBookServiceDBException extends Exception {

	enum ExceptionType {
		UNABLE_TO_CONNECT, UNABLE_TO_LOAD_DRIVER, WRONG_CREDENTIALS;
	}

	ExceptionType type;

	public AddressBookServiceDBException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}
}
