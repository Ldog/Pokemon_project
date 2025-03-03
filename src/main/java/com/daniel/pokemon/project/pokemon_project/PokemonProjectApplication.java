package com.daniel.pokemon.project.pokemon_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PokemonProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonProjectApplication.class, args);
	}

}
