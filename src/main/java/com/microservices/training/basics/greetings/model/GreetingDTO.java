package com.microservices.training.basics.greetings.model;

public class GreetingDTO {
	
	private String name;
	private String greetingmessage;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGreetingmessage() {
		return greetingmessage;
	}
	public void setGreetingmessage(String greetingmessage) {
		this.greetingmessage = greetingmessage;
	}

}
