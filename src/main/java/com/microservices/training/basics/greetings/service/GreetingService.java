package com.microservices.training.basics.greetings.service;

import com.microservices.training.basics.greetings.model.GreetingDTO;

public interface GreetingService {
	
	GreetingDTO sendGreeting();
}
