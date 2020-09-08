package com.example.demobulkhead;

import java.util.concurrent.TimeUnit;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
public class HelloHandler {
	private final Logger log = LoggerFactory.getLogger(HelloHandler.class);

	@Bulkhead(name = "hello")
	public String hello() {
		log.info("Begin hello()");
		try {
			TimeUnit.SECONDS.sleep(10);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		log.info("End hello()");
		return "Hello World!";
	}
}
