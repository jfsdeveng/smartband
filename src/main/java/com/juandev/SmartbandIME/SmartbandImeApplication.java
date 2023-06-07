package com.juandev.SmartbandIME;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SmartbandImeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartbandImeApplication.class, args);
	}

}
