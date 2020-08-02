package com.example.project3.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project3.beans.Category;
import com.example.project3.beans.Coupon;
import com.example.project3.exceptions.couponAlreadyPurchasedException;
import com.example.project3.exceptions.couponAmountZeroExcption;
import com.example.project3.exceptions.couponExpiredException;
import com.example.project3.exceptions.customerNotFoundException;
import com.example.project3.facades.CustomerFacade;
import com.example.project3.webConfig.Session;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private Map<String, Session> sessions;

	@PostMapping("purchase/{token}")
	public ResponseEntity<?> purchaseCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		try {
			Session s = sessions.get(token);
			CustomerFacade cf = (CustomerFacade) s.getFacade();
			cf.purchaseCoupon(coupon);
			return ResponseEntity.ok("coupon purchased successfully");
		} catch (couponAlreadyPurchasedException | customerNotFoundException | couponExpiredException | couponAmountZeroExcption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("getCustCoup/{token}")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable String token) {
		try {
			Session s = sessions.get(token);
			CustomerFacade cf = (CustomerFacade) s.getFacade();
			return ResponseEntity.ok(cf.getCustomerCoupons());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
//	@GetMapping("getCoupCategory/{token}/{category}")
//	public ResponseEntity<?> getCouponsByCategory(@PathVariable String token, @PathVariable Category category) {
//		try {
//			Session s = sessions.get(token);
//			CustomerFacade cf = (CustomerFacade) s.getFacade();
//			return ResponseEntity.ok(cf.getCustomerCoupons(category));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}
//	
//	@GetMapping("getCoupPrice/{token}/{price}")
//	public ResponseEntity<?> getCouponsByMaxPrice(@PathVariable String token, @PathVariable double price) {
//		try {
//			Session s = sessions.get(token);
//			CustomerFacade cf = (CustomerFacade) s.getFacade();
//			return ResponseEntity.ok(cf.getCustomerCoupons(price));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}
	
	@GetMapping("detes/{token}")
	public ResponseEntity<?> getCustomerDetails(@PathVariable String token) {
		try {
			Session s = sessions.get(token);
			CustomerFacade cf = (CustomerFacade) s.getFacade();
			return ResponseEntity.ok(cf.getCustomerDetails());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("getAllCoup/{token}")
	public ResponseEntity<?> getAllCoupons(@PathVariable String token) {
		Session s = sessions.get(token);
		CustomerFacade cf = (CustomerFacade) s.getFacade();
		return ResponseEntity.ok(cf.getAllCoupons());
	}

}
