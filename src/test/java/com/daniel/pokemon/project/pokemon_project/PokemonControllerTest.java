package com.daniel.pokemon.project.pokemon_project;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.daniel.pokemon.project.pokemon_project.controller.PokedexController;
import com.daniel.pokemon.project.pokemon_project.model.Pokemon;
import com.daniel.pokemon.project.pokemon_project.service.PokedexService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokemonControllerTest {

    @Mock
    private PokedexService pokedexService;

    @InjectMocks
    private PokedexController pokedexController;

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetPokemons() {
        // Configurar el comportamiento simulado
        Pokemon pokemon = new Pokemon();
        pokemon.setName("bulbasaur");
        pokemon.setWeight(69);
        List<Pokemon> pokemons = Arrays.asList(pokemon);
        when(pokedexService.getPokemons(0, 20)).thenReturn(pokemons);

        // Llamar al método del controlador
        List<Pokemon> result = pokedexController.getPokemons(0, 20);

        // Verificar el resultado
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("bulbasaur");
        assertThat(result.get(0).getWeight()).isEqualTo(69);
    }

    @Test
    public void testGetPokemonDetail() {
        // Configurar el comportamiento simulado
        Pokemon pokemon = new Pokemon();
        pokemon.setName("bulbasaur");
        pokemon.setWeight(69);
        when(pokedexService.getPokemonDetail(1)).thenReturn(pokemon);

        // Llamar al método del controlador
        Pokemon result = pokedexController.getPokemonDetail(1);

        // Verificar el resultado
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("bulbasaur");
        assertThat(result.getWeight()).isEqualTo(69);
        
    }
}
