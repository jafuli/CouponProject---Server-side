package com.example.project3.exceptions;

public class couponAmountZeroExcption extends Exception {
	
	public couponAmountZeroExcption() {
		super("Error purchasing coupon, coupon out of stock!");
	}

}
