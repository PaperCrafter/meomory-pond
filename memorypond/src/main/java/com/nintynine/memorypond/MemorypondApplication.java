package com.nintynine.memorypond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemorypondApplication {
	public static void main(String[] args) {
		String profile = System.getProperty("spring.profiles.active");
		if(profile == null) {
			System.setProperty("spring.profiles.active", "develop");
		}
		SpringApplication.run(MemorypondApplication.class, args);
	}

}
