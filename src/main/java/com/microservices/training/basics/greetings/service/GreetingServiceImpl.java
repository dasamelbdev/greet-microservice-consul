package com.microservices.training.basics.greetings.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microservices.training.basics.greetings.model.GreetingDTO;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Value("${greeting}")
	private String greetingMessage;
	
	
	public GreetingDTO sendGreeting(){
		
		GreetingDTO greeting = new GreetingDTO();
		String name="Dasun";
		greeting.setName(name);
		greeting.setGreetingmessage(this.greetingMessage+ " "+greeting.getName());
		return greeting;

		
	}
	
	
}
