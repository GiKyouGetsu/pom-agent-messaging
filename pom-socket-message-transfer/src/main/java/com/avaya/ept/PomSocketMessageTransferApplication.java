package com.avaya.ept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
public class PomSocketMessageTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(PomSocketMessageTransferApplication.class, args);
	}

}

