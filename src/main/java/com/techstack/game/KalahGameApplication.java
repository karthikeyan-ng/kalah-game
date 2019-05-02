package com.techstack.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class KalahGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalahGameApplication.class, args);

		log.info("Kalah Game Started...");
	}

}
