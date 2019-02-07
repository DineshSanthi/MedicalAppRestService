package com.repo.depo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.repo.depo.validator.ApplicationValidator;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class RepoDepoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(RepoDepoApplication.class, args);
	}

	@Bean
	public ApplicationValidator beforeCreateApplicationValidator() {
		return new ApplicationValidator();
	}
}
