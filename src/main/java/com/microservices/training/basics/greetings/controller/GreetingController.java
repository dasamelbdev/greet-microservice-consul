package com.microservices.training.basics.greetings.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.training.basics.greetings.model.FinalGreetingResponse;
import com.microservices.training.basics.greetings.service.GreetingService;

@RestController
@RequestMapping("/api")
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	private Environment env;

	@Autowired
	private GreetingService greetingService;

	@GetMapping("/greet")
	public ResponseEntity<FinalGreetingResponse> sendGreeting() throws Exception {

		/*
		 * command line args will be added to the spring Environment bean. Also we can
		 * access OS level environment variables through spring Environment bean.
		 * 
		 */
		logger.info("JAVA_HOME :" + env.getProperty("JAVA_HOME"));
		logger.info(env.getProperty("spring.application.name") + " service instance with port :"
				+ env.getProperty("server.port") + " called");

		FinalGreetingResponse finalResponse = greetingService.sendGreeting();
		if (finalResponse == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<FinalGreetingResponse>(finalResponse, HttpStatus.OK);

	}

}
