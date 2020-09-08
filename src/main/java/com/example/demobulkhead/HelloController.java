package com.example.demobulkhead;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private final Logger log = LoggerFactory.getLogger(HelloController.class);

	private final HelloHandler helloHandler;

	public HelloController(HelloHandler helloHandler) {
		this.helloHandler = helloHandler;
	}

	@GetMapping(path = "hello")
	public String hello() {
		return this.helloHandler.hello();
	}

	@ExceptionHandler(BulkheadFullException.class)
	public ResponseEntity<?> handleBulkheadFullException(BulkheadFullException e) {
		log.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
	}
}
