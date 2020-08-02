package com.example.project3.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project3.beans.Category;
import com.example.project3.beans.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
	
	List<Coupon> findByCompanyId(int id);
//	List<Coupon> findByCompanyIdAndCategory(int id, Category category);
//	List<Coupon> findByCompanyIdAndPriceLessThan(int id, double maxPrice);

}
