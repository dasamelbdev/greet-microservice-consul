package com.microservices.training.basics.greetings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.training.basics.greetings.model.GreetingDTO;
import com.microservices.training.basics.greetings.service.GreetingService;

@RestController
@RequestMapping("/api")
public class GreetingController {

	@Autowired
	private GreetingService greetingService;

	@GetMapping("/greet")
	public ResponseEntity<GreetingDTO> sendGreeting() {

		GreetingDTO greeting = greetingService.sendGreeting();
		if (greeting == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<GreetingDTO>(greeting, HttpStatus.OK);

	}

}
