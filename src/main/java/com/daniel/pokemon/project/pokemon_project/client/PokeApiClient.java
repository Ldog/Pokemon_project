package com.daniel.pokemon.project.pokemon_project.client;

import com.daniel.pokemon.project.pokemon_project.model.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2/")
public interface PokeApiClient {
    @GetMapping("/pokemon/{name}")
    Pokemon getPokemon(@PathVariable("name") String name);
}
