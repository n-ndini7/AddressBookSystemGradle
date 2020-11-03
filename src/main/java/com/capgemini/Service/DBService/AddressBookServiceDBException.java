package com.capgemini.Service.DBService;

//this class holds custom exceptions for sql
public class AddressBookServiceDBException extends Exception {

	enum ExceptionType {
		UNABLE_TO_CONNECT,UPDATE_FAILED,RETREIVAL_FAILED;
	}

	ExceptionType type;

	public AddressBookServiceDBException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}
}
