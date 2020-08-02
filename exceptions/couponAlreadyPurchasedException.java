package com.example.project3.exceptions;

public class couponAlreadyPurchasedException extends Exception {

	public couponAlreadyPurchasedException() {
		super("Error purchasing coupon, coupon already purchased!");
	}
	
}
