package com.example.project3.exceptions;

public class customerNotFoundException extends Exception {
	
	public customerNotFoundException() {
		super("Customer was not found!");
	}

}
