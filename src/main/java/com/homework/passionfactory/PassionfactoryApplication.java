package com.homework.passionfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class PassionfactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassionfactoryApplication.class, args);
	}


}
