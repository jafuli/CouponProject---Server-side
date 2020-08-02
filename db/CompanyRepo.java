package com.example.project3.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project3.beans.Company;
import com.example.project3.exceptions.companyNotFoundException;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
	
	Optional<Company> findByEmailAndPassword(String email, String password) throws companyNotFoundException;

}
