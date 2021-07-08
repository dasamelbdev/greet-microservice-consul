package com.microservices.training.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.microservices.training.basics.greetings.config.AppConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

//@LoadBalancerClients({ @LoadBalancerClient(name = "greeting-service", configuration = AppConfig.class)
//
//})
public class MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

}
