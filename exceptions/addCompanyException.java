package com.example.project3.exceptions;

public class addCompanyException extends Exception {

	public addCompanyException() {
		super("Error adding company, name or email already exists!");
	}

}
