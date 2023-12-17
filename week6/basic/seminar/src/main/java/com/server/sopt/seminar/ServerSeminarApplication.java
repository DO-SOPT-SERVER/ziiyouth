package com.server.sopt.seminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ServerSeminarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSeminarApplication.class, args);
	}

}
