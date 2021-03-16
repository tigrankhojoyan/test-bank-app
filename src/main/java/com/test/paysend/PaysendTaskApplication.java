package com.test.paysend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class PaysendTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaysendTaskApplication.class, args);
	}

}
