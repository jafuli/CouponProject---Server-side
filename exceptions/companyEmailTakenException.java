package com.example.project3.exceptions;

public class companyEmailTakenException extends Exception {
	
	public companyEmailTakenException() {
		super("Email already taken, update unsuccessfull");
	}

}
