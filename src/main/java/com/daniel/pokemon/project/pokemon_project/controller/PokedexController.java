package com.daniel.pokemon.project.pokemon_project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.daniel.pokemon.project.pokemon_project.model.Pokemon;
import com.daniel.pokemon.project.pokemon_project.service.PokedexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PokedexController {

    private final PokedexService pokedexService;

    public PokedexController(PokedexService pokedexService) {
        this.pokedexService = pokedexService;
    }

    @GetMapping("/pokemons")
    public List<Pokemon> getPokemons(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return pokedexService.getPokemons(page, size);
    }

    @GetMapping("/pokemon/{id}")
    public Pokemon getPokemonDetail(@PathVariable int id) {
        return pokedexService.getPokemonDetail(id);
    }
    
}
