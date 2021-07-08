package com.microservices.training.basics.greetings.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "name-service")
public interface NameServiceProxy {

	@GetMapping("/api/name")
	public ResponseEntity<String> getName();

}
