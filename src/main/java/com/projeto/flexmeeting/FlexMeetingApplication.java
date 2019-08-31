package com.projeto.flexmeeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlexMeetingApplication {
	
	public static final int TABLE_MAX_ROWS = 10;
	
	public static void main(String[] args) {
		SpringApplication.run(FlexMeetingApplication.class, args);
	}
}
