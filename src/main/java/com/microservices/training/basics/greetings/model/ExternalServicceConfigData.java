package com.microservices.training.basics.greetings.model;

public class ExternalServicceConfigData {
	
	private String servicceName;
	private String servicePort;

	public String getServicceName() {
		return servicceName;
	}

	public void setServicceName(String servicceName) {
		this.servicceName = servicceName;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

}
