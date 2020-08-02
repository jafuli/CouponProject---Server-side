package com.example.project3.facades;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.project3.beans.Category;
import com.example.project3.beans.Coupon;
import com.example.project3.beans.Customer;
import com.example.project3.exceptions.couponAlreadyPurchasedException;
import com.example.project3.exceptions.couponAmountZeroExcption;
import com.example.project3.exceptions.couponExpiredException;
import com.example.project3.exceptions.customerNotFoundException;

@Service
@Scope("prototype")
public class CustomerFacade extends ClientFacade {

	private int customerID;

	@Override
	public boolean login(String email, String password) throws customerNotFoundException {
		Customer c = cusRepo.findByEmailAndPassword(email, password).orElseThrow(customerNotFoundException::new);
		customerID = c.getId();
		return true;
	}

	public void purchaseCoupon(Coupon coupon) throws customerNotFoundException, couponExpiredException, couponAmountZeroExcption, couponAlreadyPurchasedException {
		Date now = new Date(System.currentTimeMillis());
		Date expireDate = coupon.getEndDate();
		Customer c = cusRepo.findById(customerID).orElseThrow();
		if (coupon.getAmount() == 0) { // conditions
			System.out.println("a");
			throw new couponAmountZeroExcption();}
		if (now.after(expireDate)) {
			System.out.println("b");
			throw new couponExpiredException();}
		if (getCustomerCoupons().contains(coupon)) {
			System.out.println("c");
			throw new couponAlreadyPurchasedException();}

		// purchase
		c.getCoupons().add(coupon); // add coupon to customer
		coupon.setAmount(coupon.getAmount() - 1); // decrease amount by 1
		cusRepo.save(c); // update customer
		coupRepo.save(coupon); // update coupon

	}

	public Set<Coupon> getCustomerCoupons() throws customerNotFoundException {
		return cusRepo.findById(customerID).orElseThrow(customerNotFoundException::new).getCoupons();
	}

//	public Set<Coupon> getCustomerCoupons(Category category) throws customerNotFoundException {
//		Set<Coupon> coupons = getCustomerCoupons();
//		coupons.removeIf(coupon -> coupon.getCategory() != category);
//		return coupons;
//	}
//
//	public Set<Coupon> getCustomerCoupons(double maxPrice) throws customerNotFoundException {
//		Set<Coupon> coupons = getCustomerCoupons();
//		coupons.removeIf(coupon -> coupon.getPrice() > maxPrice);
//		return coupons;
//	}

	public Customer getCustomerDetails() throws customerNotFoundException {
		return cusRepo.findById(customerID).orElseThrow(customerNotFoundException::new);
	}

	public List<Coupon> getAllCoupons() {
		return coupRepo.findAll();
	}

}
