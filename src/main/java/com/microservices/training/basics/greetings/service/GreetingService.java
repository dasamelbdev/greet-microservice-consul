package com.microservices.training.basics.greetings.service;

import com.microservices.training.basics.greetings.model.FinalResponse;

public interface GreetingService {
	
	FinalResponse sendGreeting() throws Exception;
}
