package com.example.project3.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.project3.beans.Coupon;
import com.example.project3.beans.Customer;
import com.example.project3.db.CouponRepo;
import com.example.project3.db.CustomerRepo;

@Component
public class CouponExpirationDailyJob extends Thread {

	@Autowired
	private CouponRepo coupRepo;
	@Autowired
	private CustomerRepo cusRepo;
	private boolean quit = true;

	public CouponExpirationDailyJob() {
	}

	@Override
	public void run() {
		System.out.println("Daily Job Started!");
		while (quit) {
			try {
				List<Coupon> coupons = coupRepo.findAll();
				for (Coupon coupon : coupons) {
					long end = coupon.getEndDate().getTime();
					long now = System.currentTimeMillis();
					if (now > end) {
						// clear history
						for (Customer customer : cusRepo.findAll()) {
							customer.getCoupons().remove(coupon);
							cusRepo.save(customer);
						}
						// delete coupon
						coupRepo.delete(coupon);
					}
				}
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				System.out.println("Daily job stopped");
			}
		}

	}

	public void stopJob() {
		quit = false;
		interrupt();
	}

}
