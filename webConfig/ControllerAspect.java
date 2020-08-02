package com.example.project3.webConfig;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

	@Autowired
	private Map<String, Session> sessions;

	@Pointcut("execution(* com.example.project3.web..*(String,..))")
	public void tokenMethods() {
	}

	@Around("tokenMethods() && args(token,..)")
	public ResponseEntity<?> checkToken(ProceedingJoinPoint jp, String token) throws Throwable {
		Session s = sessions.get(token);
		if (s == null ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
		}
		if (System.currentTimeMillis() - s.getLastAccessed() > 1000 * 60 * 30) {
			sessions.remove(token);
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("session timeout");
		}
		ResponseEntity<?> r = (ResponseEntity<?>) jp.proceed();
		s.setLastAccessed(System.currentTimeMillis());
		return r;
	}

}
