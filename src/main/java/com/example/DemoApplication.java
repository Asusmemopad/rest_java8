package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	private static Logger logger = Logger.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		logger.info("Starting SpringBootDemoApplication...");
		SpringApplication.run(DemoApplication.class, args);
		logger.info("Run SpringBootDemoApplication...");
	}
}
