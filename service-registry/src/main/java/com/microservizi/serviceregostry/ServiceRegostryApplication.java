package com.microservizi.serviceregostry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegostryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegostryApplication.class, args);
	}

}
