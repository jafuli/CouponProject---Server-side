package com.example.project3.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project3.beans.Category;
import com.example.project3.beans.Coupon;
import com.example.project3.facades.CompanyFacade;
import com.example.project3.webConfig.Session;

@RestController
@RequestMapping("company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {
	
	@Autowired
	private Map<String, Session> sessions;
	
	@PostMapping("addCoup/{token}")
	public ResponseEntity<?> addCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		try {
			Session s = sessions.get(token);
			CompanyFacade cf = (CompanyFacade) s.getFacade();
			cf.addCoupon(coupon);
			return ResponseEntity.ok("coupon added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("updateCoup/{token}")
	public ResponseEntity<?> updateCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		try {
			Session s = sessions.get(token);
			CompanyFacade cf = (CompanyFacade) s.getFacade();
			cf.updateCoupon(coupon);
			return ResponseEntity.ok("coupon updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("delCoup/{token}/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable String token, @PathVariable int id) {
		try {
			Session s = sessions.get(token);
			CompanyFacade cf = (CompanyFacade) s.getFacade();
			cf.deleteCoupon(id);
			return ResponseEntity.ok("coupon deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("getAllCoup/{token}")
	public ResponseEntity<?> getAllCoupons(@PathVariable String token) {
			Session s = sessions.get(token);
			CompanyFacade cf = (CompanyFacade) s.getFacade();
			return ResponseEntity.ok(cf.getCompanyCoupons());
	}

//	@GetMapping("getCoupCategory/{token}/{category}")
//	public ResponseEntity<?> getCouponsByCategory(@PathVariable String token, @PathVariable Category category) {
//		Session s = sessions.get(token);
//		CompanyFacade cf = (CompanyFacade) s.getFacade();
//		return ResponseEntity.ok(cf.getCompanyCoupons(category));
//	}
//	
//	@GetMapping("getCoupPrice/{token}/{price}")
//	public ResponseEntity<?> getCouponsByMaxPrice(@PathVariable String token, @PathVariable double price) {
//		Session s = sessions.get(token);
//		CompanyFacade cf = (CompanyFacade) s.getFacade();
//		return ResponseEntity.ok(cf.getCompanyCoupons(price));
//	}
	
	@GetMapping("detes/{token}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable String token) {
		try {
			Session s = sessions.get(token);
			CompanyFacade cf = (CompanyFacade) s.getFacade();
			return ResponseEntity.ok(cf.getCompanyDetails());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
