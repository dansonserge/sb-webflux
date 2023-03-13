package com.sergedanson.spwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class SpWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpWebfluxApplication.class, args);
	}

}
