package com.example.project3.exceptions;

public class couponUpdateException extends Exception {

	public couponUpdateException() {
		super("Can not change company or title, update unsuccessfull");
	}
}
