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

import com.example.project3.beans.Company;
import com.example.project3.beans.Customer;
import com.example.project3.facades.AdminFacade;
import com.example.project3.webConfig.Session;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	private Map<String, Session> sessions;

	@PostMapping("addComp/{token}")
	public ResponseEntity<?> addCompany(@PathVariable String token, @RequestBody Company company) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			af.addCompany(company);
			return ResponseEntity.ok("company added");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("updateComp/{token}")
	public ResponseEntity<?> updateCompany(@PathVariable String token, @RequestBody Company company) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			af.updateCompany(company);
			return ResponseEntity.ok("company updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("delComp/{token}/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable String token, @PathVariable int id) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			af.deleteCompany(id);
			return ResponseEntity.ok("company deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("getAllComp/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
		Session s = sessions.get(token);
		AdminFacade af = (AdminFacade) s.getFacade();
		return ResponseEntity.ok(af.getAllComapnies());
	}
	
	@GetMapping("oneComp/{token}/{id}")
	public ResponseEntity<?> getOneCompany(@PathVariable String token, @PathVariable int id) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			return ResponseEntity.ok(af.getOneCompany(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("addCus/{token}")
	public ResponseEntity<?> addCustomer(@PathVariable String token, @RequestBody Customer customer) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			af.addCustomer(customer);
			return ResponseEntity.ok("customer added");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("updateCus/{token}")
	public ResponseEntity<?> updateCustomer(@PathVariable String token, @RequestBody Customer customer) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			af.updateCustomer(customer);
			return ResponseEntity.ok("customer updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("delCus/{token}/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable String token, @PathVariable int id) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			af.deleteCustomer(id);
			return ResponseEntity.ok("customer deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("getAllCus/{token}")
	public ResponseEntity<?> getAllCustomers(@PathVariable String token) {
		Session s = sessions.get(token);
		AdminFacade af = (AdminFacade) s.getFacade();
		return ResponseEntity.ok(af.getAllCustomers());
	}
	
	@GetMapping("oneCus/{token}/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable String token, @PathVariable int id) {
		try {
			Session s = sessions.get(token);
			AdminFacade af = (AdminFacade) s.getFacade();
			return ResponseEntity.ok(af.getOneCustomer(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
