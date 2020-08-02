package com.example.project3.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.project3.exceptions.adminLoginException;
import com.example.project3.exceptions.companyNotFoundException;
import com.example.project3.exceptions.customerNotFoundException;
import com.example.project3.facades.AdminFacade;
import com.example.project3.facades.ClientFacade;
import com.example.project3.facades.CompanyFacade;
import com.example.project3.facades.CustomerFacade;

@Component
public class LoginManager {
	
	public LoginManager() {}
	
	@Autowired
	CompanyFacade cf;
	@Autowired
	CustomerFacade cuf;
	@Autowired
	AdminFacade af;
	public ClientFacade login(String email, String password, ClientType type) throws customerNotFoundException, companyNotFoundException, adminLoginException {
		
		if (type.equals(ClientType.Administrator)) {
//			AdminFacade af = new AdminFacade();
			if (af.login(email, password))
				return af;
		}
		
		if (type.equals(ClientType.Company)) {
//			CompanyFacade cf = new CompanyFacade();
			if (cf.login(email, password)==true)
				return cf;
		}
		
		if (type.equals(ClientType.Customer)) {
//			CustomerFacade cuf = new CustomerFacade();
			if (cuf.login(email, password))
				return cuf;
		}
		
		return null;
		
	}

}
