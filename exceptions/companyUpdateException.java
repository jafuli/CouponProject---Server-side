package com.example.project3.exceptions;

public class companyUpdateException extends Exception {

	public companyUpdateException() {
		super("Can not change company name, update unsuccessfull");
	}
}
