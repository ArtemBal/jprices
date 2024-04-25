package com.artem.balan.jprices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JpricesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpricesApplication.class, args);
	}

}
