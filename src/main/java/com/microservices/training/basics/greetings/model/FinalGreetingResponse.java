package com.microservices.training.basics.greetings.model;

public class FinalGreetingResponse {
	
	private GreetingDTO greetingDTO;
	private ExternalServicceConfigData externalServicceConfigData;

	public GreetingDTO getGreetingDTO() {
		return greetingDTO;
	}

	public void setGreetingDTO(GreetingDTO greetingDTO) {
		this.greetingDTO = greetingDTO;
	}

	public ExternalServicceConfigData getExternalServicceConfigData() {
		return externalServicceConfigData;
	}

	public void setExternalServicceConfigData(ExternalServicceConfigData externalServicceConfigData) {
		this.externalServicceConfigData = externalServicceConfigData;
	}

}
