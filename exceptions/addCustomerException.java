package com.example.project3.exceptions;

public class addCustomerException extends Exception {
	
	public addCustomerException() {
		super("Error adding customer, email already exists!");
	}

}
