package com.avaya.ept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.avaya.ept")
public class PomSocketMessageTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(PomSocketMessageTransferApplication.class, args);
	}

}

