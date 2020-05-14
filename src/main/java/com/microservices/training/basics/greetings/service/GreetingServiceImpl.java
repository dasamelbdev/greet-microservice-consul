package com.microservices.training.basics.greetings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.training.basics.greetings.model.GreetingDTO;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Value("${greeting}")
	private String greetingMessage;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public GreetingDTO sendGreeting() throws Exception{
		
		GreetingDTO greeting = new GreetingDTO();
		String nameServiceUrl="http://name-service/api/name";
		ResponseEntity<String> response=restTemplate.getForEntity(nameServiceUrl, String.class);
	
		ObjectMapper mapper= new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode name = root.path("name");
			
		greeting.setName(name.asText());
		greeting.setGreetingmessage(this.greetingMessage+ " "+greeting.getName());
		return greeting;

		
	}
	
	
}
