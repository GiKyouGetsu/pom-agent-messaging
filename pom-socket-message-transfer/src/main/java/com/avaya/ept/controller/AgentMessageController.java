package com.avaya.ept.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.avaya.ept.pom.response.AgentInfo;
import com.avaya.ept.pom.response.AgentResponse;


@Controller
public class AgentMessageController {

	private static final Logger LOG = Logger.getLogger(AgentMessageController.class);
	
	@Autowired
	SimpMessagingTemplate templete;
	
	@Autowired
	SimpMessageSendingOperations simpMessageSendingOperations;
	

	@MessageMapping("/user")
	@SendTo("/topic/user")
	public AgentResponse getAgent(AgentInfo agent) {
		LOG.info("Agent recieved");
		LOG.info("Recieved User: " + agent.toString());
		
		templete.convertAndSend("topic/feedback", agent.toString());
		return new AgentResponse("Response agent : " + agent.getName() );
	}
}
