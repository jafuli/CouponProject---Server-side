package com.example.project3.facades;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.project3.db.CompanyRepo;
import com.example.project3.db.CouponRepo;
import com.example.project3.db.CustomerRepo;
import com.example.project3.exceptions.adminLoginException;
import com.example.project3.exceptions.companyNotFoundException;
import com.example.project3.exceptions.customerNotFoundException;

public abstract class ClientFacade {

	@Autowired
	protected CompanyRepo compRepo;
	@Autowired
	protected CustomerRepo cusRepo;
	@Autowired
	protected CouponRepo coupRepo;
	
	protected abstract boolean login(String email, String password) throws adminLoginException, companyNotFoundException, customerNotFoundException;
	
}
