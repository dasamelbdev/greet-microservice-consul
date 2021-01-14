package com.microservices.training.basics.greetings.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.training.basics.greetings.model.ExternalServicceConfigData;
import com.microservices.training.basics.greetings.model.FinalResponse;
import com.microservices.training.basics.greetings.model.GreetingDTO;

@Service
public class GreetingServiceImpl implements GreetingService {

	private static final Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

	@Value("${greeting}")
	private String greetingMessage;

	@Autowired
	private RestTemplate restTsemplate;

	public FinalResponse sendGreeting() throws Exception {

		String nameServiceUrl = "http://name-service/api/name";
		ResponseEntity<String> response = restTsemplate.getForEntity(nameServiceUrl, String.class);
		return processJsonResponse(response);

	}

	private FinalResponse processJsonResponse(ResponseEntity<String> response) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		logger.debug("root node :" + root.asText());

		JsonNode name = root.path("nameDTO").path("name");
		JsonNode serviceName = root.path("instanceData").path("serviceName");
		JsonNode servicePort = root.path("instanceData").path("serverPort");

		GreetingDTO greeting = new GreetingDTO();
		greeting.setName(name.asText());
		greeting.setGreetingmessage(this.greetingMessage + " " + greeting.getName());

		ExternalServicceConfigData externalServicceConfigData = new ExternalServicceConfigData();
		externalServicceConfigData.setServicceName(serviceName.asText());
		externalServicceConfigData.setServicePort(servicePort.asText());

		FinalResponse finalResponse = new FinalResponse();
		finalResponse.setGreetingDTO(greeting);
		finalResponse.setExternalServicceConfigData(externalServicceConfigData);
		return finalResponse;
	}

}
