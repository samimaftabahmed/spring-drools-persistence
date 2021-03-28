package com.sam.springdroolspersistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.sam.springdroolspersistence.entity", "org.drools.persistence.info"})
@EnableJpaRepositories
@SpringBootApplication
public class SpringDroolsPersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDroolsPersistenceApplication.class, args);
	}

}
