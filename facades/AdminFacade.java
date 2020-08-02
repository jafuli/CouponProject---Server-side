package com.example.project3.facades;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.project3.beans.Company;
import com.example.project3.beans.Coupon;
import com.example.project3.beans.Customer;
import com.example.project3.exceptions.addCompanyException;
import com.example.project3.exceptions.addCustomerException;
import com.example.project3.exceptions.adminLoginException;
import com.example.project3.exceptions.companyEmailTakenException;
import com.example.project3.exceptions.companyNotFoundException;
import com.example.project3.exceptions.companyUpdateException;
import com.example.project3.exceptions.customerNotFoundException;
import com.example.project3.exceptions.updateCustomerException;

@Service
@Scope("prototype")
public class AdminFacade extends ClientFacade {

//	@Autowired
//	private CustomerRepo cusRepo;
//	@Autowired
//	private CouponRepo coupRepo;
//	@Autowired
//	private CompanyRepo compRepo;

	@Override
	public boolean login(String email, String password) throws adminLoginException {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		} else {
			throw new adminLoginException();
		}
	}

	public void addCompany(Company company) throws addCompanyException {
		List<Company> companies = compRepo.findAll();
		for (Company company2 : companies) {
			if (company2.getName().equals(company.getName()) || company2.getEmail().equals(company.getEmail())) {
				throw new addCompanyException();
			}
		}
		compRepo.save(company);
	}

	public void updateCompany(Company company) throws companyNotFoundException, companyUpdateException, companyEmailTakenException {
		Company c1 = compRepo.findById(company.getId()).orElseThrow(companyNotFoundException::new);
		if (!c1.getName().equals(company.getName())) { // name change check
			throw new companyUpdateException();
		}
		List<Company> companies = compRepo.findAll();
		companies.remove(c1);
		for (Company company2 : companies) {
			if (company2.getEmail().equals(company.getEmail()))
				throw new companyEmailTakenException();
		}
		compRepo.save(company);
	}

	@Transactional
	public void deleteCompany(int id) throws companyNotFoundException {
//		coupRepo.findByCompany(compRepo.findById(id).orElseThrow(companyNotFoundException::new));

		// delete purchased coupons history from customers_vs_coupons
		List<Customer> customers = cusRepo.findAll();
		for (Customer customer : customers) {
//			for (Coupon coupon : customer.getCoupons()) {
//				if (coupon.getCompany().getId() == id) {
//					customer.getCoupons().remove(coupon);
				customer.getCoupons().removeIf(coup -> coup.getCompany().getId() == id);
				cusRepo.save(customer);
//				}

//			}
			// delete company coupons from coupons
			List<Coupon> coupons = coupRepo.findAll();
			for (Coupon coupon : coupons) {
				if (coupon.getCompany().getId() == id)
					coupRepo.delete(coupon);
			}
		}

		// delete company
		compRepo.delete(compRepo.findById(id).orElseThrow(companyNotFoundException::new));
	}

	public List<Company> getAllComapnies() {
		return compRepo.findAll();
	}

	public Company getOneCompany(int id) throws companyNotFoundException {
		return compRepo.findById(id).orElseThrow(companyNotFoundException::new);
	}

	public void addCustomer(Customer customer) throws addCustomerException {
		List<Customer> customers = cusRepo.findAll();
		for (Customer customer2 : customers) {
			if (customer2.getEmail().equals(customer.getEmail())) {
				throw new addCustomerException();
			}
		}
		cusRepo.save(customer);

	}

	public void updateCustomer(Customer customer) throws customerNotFoundException, updateCustomerException {
		Customer c = cusRepo.findById(customer.getId()).orElseThrow(customerNotFoundException::new);
		List<Customer> customers = cusRepo.findAll();
		customers.remove(c);
		for (Customer customer2 : customers) {
			if (customer2.getEmail().equals(customer.getEmail()))
				throw new updateCustomerException();
		}
		cusRepo.save(customer);

	}

	public void deleteCustomer(int id) throws customerNotFoundException {
		// delete purchased coupons history from customers_vs_coupons
		Customer c = cusRepo.findById(id).orElseThrow(customerNotFoundException::new);
		c.getCoupons().clear();
		cusRepo.save(c);
		// delete customer
		cusRepo.delete(c);
	}

	public List<Customer> getAllCustomers() {
		return cusRepo.findAll();
	}

	public Customer getOneCustomer(int id) throws customerNotFoundException {
		return cusRepo.findById(id).orElseThrow(customerNotFoundException::new);
	}

}
