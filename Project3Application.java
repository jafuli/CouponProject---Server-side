package com.example.project3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.project3.login.CouponExpirationDailyJob;

@SpringBootApplication
public class Project3Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Project3Application.class, args);
		CouponExpirationDailyJob job = ctx.getBean(CouponExpirationDailyJob.class);
		job.start();
	}

}
