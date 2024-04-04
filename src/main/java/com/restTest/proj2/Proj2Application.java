package com.restTest.proj2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Proj2Application {

	public static void main(String[] args) {
		SpringApplication.run(Proj2Application.class, args);
	}

}
