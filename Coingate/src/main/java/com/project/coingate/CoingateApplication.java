package com.project.coingate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class CoingateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoingateApplication.class, args);
	}

}
