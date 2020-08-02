package com.example.project3.login;

import java.util.Map;
import java.util.UUID;

import javax.websocket.ClientEndpoint;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project3.facades.ClientFacade;
import com.example.project3.webConfig.Session;

@RestController
@RequestMapping("client")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private Map<String, Session> sessions;
	@Autowired
	private LoginManager lm;
	
	@GetMapping("login/{email}/{password}/{type}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password, @PathVariable ClientType type) {
		try {
			ClientFacade facade = lm.login(email, password, type);
			String token = UUID.randomUUID().toString();
			Session session = new Session(facade, System.currentTimeMillis());
			sessions.put(token, session);
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("logout/{token}")
	public ResponseEntity<?> logout(@PathVariable String token) {
		sessions.remove(token);
		return ResponseEntity.ok("logged out");
	}
	
}
