package com.example.project3.exceptions;

public class invalidCouponDateException extends Exception {

	public invalidCouponDateException() {
		super("Error adding coupon, expiration date must be AFTER start date!");
	}
}
