package com.microservices.training.basics.greetings.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.training.basics.greetings.feignclient.NameServiceProxy;
import com.microservices.training.basics.greetings.model.ExternalServicceConfigData;
import com.microservices.training.basics.greetings.model.FinalGreetingResponse;
import com.microservices.training.basics.greetings.model.GreetingDTO;

@Service
public class GreetingServiceImpl implements GreetingService {

	private static final Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

	@Value("${greeting}")
	private String greetingMessage;

//	@Autowired
//	private RestTemplate restTsemplate;

	@Autowired
	private LoadBalancerClientFactory balancerClientFactory;

	@Autowired
	private NameServiceProxy nameServiceProxy;

	public FinalGreetingResponse sendGreeting() throws Exception {

		/*
		 * String nameServiceUrl = "http://name-service/api/name";
		 * 
		 * RoundRobinLoadBalancer roundRobinLoadBalancer =
		 * balancerClientFactory.getInstance("name-service",
		 * RoundRobinLoadBalancer.class); ServiceInstance serviceInstance =
		 * roundRobinLoadBalancer.choose().block().getServer(); String newNameServiceUrl
		 * = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() +
		 * "/api/name"; logger.info("newNameServiceUrl :" + newNameServiceUrl); //
		 * ResponseEntity<String> response = restTsemplate.getForEntity(nameServiceUrl,
		 * // String.class); ResponseEntity<String> response =
		 * restTsemplate.getForEntity(newNameServiceUrl, String.class);
		 */

		ResponseEntity<String> response = nameServiceProxy.getName();
		return processJsonResponse(response);

	}

	private FinalGreetingResponse processJsonResponse(ResponseEntity<String> response) throws Exception {

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

		FinalGreetingResponse finalResponse = new FinalGreetingResponse();
		finalResponse.setGreetingDTO(greeting);
		finalResponse.setExternalServicceConfigData(externalServicceConfigData);
		return finalResponse;
	}

}
