package com.example.project3.exceptions;

public class couponExpiredException extends Exception {
	
	public couponExpiredException() {
		super("Error purchasing coupon, coupon expired!");
	}
	
}
