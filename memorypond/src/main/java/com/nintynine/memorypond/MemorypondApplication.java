package com.nintynine.memorypond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {MemorypondApplication.class})
public class MemorypondApplication {
	public static void main(String[] args) {
		String profile = System.getProperty("spring.profiles.active");
		if(profile == null) {
			System.setProperty("spring.profiles.active", "develop");
		}
		SpringApplication.run(MemorypondApplication.class, args);
	}

}
