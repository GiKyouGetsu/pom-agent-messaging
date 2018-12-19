package com.avaya.ept.pom.response;

public class AgentInfo {
	
	private String name;
	public AgentInfo() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AgentInfo [name=" + name + "]";
	}
	
}
