package com.avaya.ept.pom.response;

public class AgentResponse {
	private String message;
	
	public AgentResponse() {}
	public AgentResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AgentResponse [message=" + message + "]";
	}
}
