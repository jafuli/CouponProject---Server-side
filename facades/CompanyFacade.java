package com.example.project3.facades;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.project3.beans.Category;
import com.example.project3.beans.Company;
import com.example.project3.beans.Coupon;
import com.example.project3.beans.Customer;
import com.example.project3.exceptions.companyNotFoundException;
import com.example.project3.exceptions.couponAlreadyExistsException;
import com.example.project3.exceptions.couponNotFoundException;
import com.example.project3.exceptions.couponUpdateException;
import com.example.project3.exceptions.invalidCouponDateException;

@Service
@Scope("prototype")
public class CompanyFacade extends ClientFacade {

	private int companyID;

	@Override
	public boolean login(String email, String password) throws companyNotFoundException {
		Company c = compRepo.findByEmailAndPassword(email, password).orElseThrow(companyNotFoundException::new);
		companyID = c.getId();
		return true;
	}

	public void addCoupon(Coupon coupon) throws couponAlreadyExistsException, companyNotFoundException, invalidCouponDateException {
		for (Coupon coupon2 : getCompanyCoupons()) {
			if (coupon2.getTitle().equals(coupon.getTitle())) 
				throw new couponAlreadyExistsException();
			
		}
		if (coupon.getStartDate().after(coupon.getEndDate()))
			throw new invalidCouponDateException();
		coupon.setCompany(getCompanyDetails());
		coupRepo.save(coupon);
	}

	// test if company and id can be updated
	public void updateCoupon(Coupon coupon) throws couponAlreadyExistsException, couponUpdateException, couponNotFoundException {
		// company change check
		if (coupon.getCompany().getId() != companyID) {
			throw new couponUpdateException();
		}
		// title change check
		Coupon coupon1 = coupRepo.findById(coupon.getId()).orElseThrow(couponNotFoundException::new);
		if (!coupon.getTitle().equals(coupon1.getTitle()))
			throw new couponUpdateException();
		// coupon title exists check
		List<Coupon> coupons = getCompanyCoupons();
		coupons.remove(coupon);
		for (Coupon coupon2 : coupons) {
			if (coupon2.getTitle().equals(coupon.getTitle())) {
				throw new couponAlreadyExistsException();
			}
		}
		coupRepo.save(coupon);
	}

	public void deleteCoupon(int id) throws couponNotFoundException {
		// check if coupon belongs to the company
		if (companyID == coupRepo.findById(id).orElseThrow(couponNotFoundException::new).getCompany().getId()) {
			// belongs
			// delete purchased coupons history from customers_vs_coupons
			for (Customer customer : cusRepo.findAll()) {
				for (Coupon coupon : customer.getCoupons()) {
					if (coupon.getId() == id) {
						customer.getCoupons().remove(coupon);
						cusRepo.save(customer);
					}
				}
			}
			// delete coupon
			coupRepo.delete(coupRepo.findById(id).orElseThrow(couponNotFoundException::new));
		} else {
			// doesnt belong
			throw new couponNotFoundException();
		}
	}

	public List<Coupon> getCompanyCoupons() {
		return coupRepo.findByCompanyId(companyID);
	}

//	public List<Coupon> getCompanyCoupons(Category category) {
//		return coupRepo.findByCompanyIdAndCategory(companyID, category);
//	}
//
//	public List<Coupon> getCompanyCoupons(double maxPrice) {
//		return coupRepo.findByCompanyIdAndPriceLessThan(companyID, maxPrice);
//	}

	public Company getCompanyDetails() throws companyNotFoundException {
		Company c = compRepo.findById(companyID).orElseThrow(companyNotFoundException::new);
//		c.setCoupons(getCompanyCoupons());
		return c;
	}

}
