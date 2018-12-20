package com.avaya.ept.socketconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.avaya.ept.pom.response.AgentResponse;

@EnableScheduling
@Configuration
public class SchedulerConfig {
    @Autowired
    SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 10000)
    public void sendAdhocMessages() {
        template.convertAndSend("/topic/user", new AgentResponse("Fixed Delay Scheduler"));
//        template.convertAndSend("/topic/feedback", new AgentResponse("Feedback Delay Scheduler"));

    }
}
