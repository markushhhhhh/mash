package com.example.artistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ArtistApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtistApiApplication.class, args);
	}

}
