package com.microservices.training.basics.greetings.service;

import com.microservices.training.basics.greetings.model.FinalGreetingResponse;

public interface GreetingService {
	
	FinalGreetingResponse sendGreeting() throws Exception;
}
