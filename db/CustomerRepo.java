package com.example.project3.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project3.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByEmailAndPassword(String email, String password);

}
