package com.isaev.ee.healthcarecrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

@SpringBootApplication
@ComponentScan({ "com.isaev.ee.healthcarecrm.components", "com.isaev.ee.healthcarecrm.dao",
		"com.isaev.ee.healthcarecrm.selectionfactories", "com.isaev.ee.healthcarecrm.unitsofwork" })
@EntityScan("com.isaev.ee.healthcarecrm.domain")
@EnableAutoConfiguration
public class HealthcareCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareCrmApplication.class, args);
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

}
