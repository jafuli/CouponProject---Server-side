package com.example.project3.exceptions;

public class updateCustomerException extends Exception {

	public updateCustomerException() {
		super("Error updating customer, email already exists!");
	}
}
