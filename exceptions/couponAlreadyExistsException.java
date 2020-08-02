package com.example.project3.exceptions;

public class couponAlreadyExistsException extends Exception {
	
	public couponAlreadyExistsException() {
		super("Error adding coupon, coupon title already exists!");
	}

}
