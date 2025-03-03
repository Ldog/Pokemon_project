package com.daniel.pokemon.project.pokemon_project.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.daniel.pokemon.project.pokemon_project.model.Pokemon;
import com.daniel.pokemon.project.pokemon_project.model.Type;
import com.daniel.pokemon.project.pokemon_project.model.Ability;
import com.daniel.pokemon.project.pokemon_project.model.Evolution;

@Service
public class PokedexService {

    private static final String POKEAPI_BASE_URL = "https://pokeapi.co/api/v2/pokemon";

    public List<Pokemon> getPokemons(int page, int size) {
        List<Pokemon> pokemons = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = POKEAPI_BASE_URL + "?offset=" + (page * size) + "&limit=" + size;
        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonObject = new JSONObject(response);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String pokemonUrl = result.getString("url");
            String pokemonResponse = restTemplate.getForObject(pokemonUrl, String.class);
            JSONObject pokemonObject = new JSONObject(pokemonResponse);

            Pokemon pokemon = new Pokemon();
            pokemon.setWeight(pokemonObject.getInt("weight"));
            pokemon.setName(pokemonObject.getString("name"));

            JSONArray typesArray = pokemonObject.getJSONArray("types");
            List<Type> types = new ArrayList<>();
            for (int j = 0; j < typesArray.length(); j++) {
                JSONObject typeObject = typesArray.getJSONObject(j).getJSONObject("type");
                Type type = new Type();
                type.setName(typeObject.getString("name"));
                type.setUrl(typeObject.getString("url"));
                types.add(type);
            }
            pokemon.setTypes(types);

            JSONArray abilitiesArray = pokemonObject.getJSONArray("abilities");
            List<Ability> abilities = new ArrayList<>();
            for (int j = 0; j < abilitiesArray.length(); j++) {
                JSONObject abilityObject = abilitiesArray.getJSONObject(j).getJSONObject("ability");
                Ability ability = new Ability();
                ability.setName(abilityObject.getString("name"));
                ability.setUrl(abilityObject.getString("url"));
                abilities.add(ability);
            }
            pokemon.setAbilities(abilities);

            pokemon.setImageUrl(pokemonObject.getJSONObject("sprites").getString("front_default"));

            pokemons.add(pokemon);
        }

        return pokemons;
    }

    public Pokemon getPokemonDetail(int id){
        RestTemplate restTemplate = new RestTemplate();
        String url = POKEAPI_BASE_URL + "/" + id;
        // Obtener la respuesta del servicio web
        String response = restTemplate.getForObject(url, String.class);
        // Convertir la respuesta a un objeto JSON
        JSONObject pokemonObject = new JSONObject(response);
        Pokemon pokemon = new Pokemon();
        // Obtener el nombre y el peso del pokemon
        pokemon.setName(pokemonObject.getString("name"));
        pokemon.setWeight(pokemonObject.getInt("weight"));
        
        JSONArray typesArray = pokemonObject.getJSONArray("types");
        List<Type> types = new ArrayList<>();
        // Iterar sobre el arreglo de tipos ya que cada tipo es un objeto
        for (int j = 0; j < typesArray.length(); j++) {
            JSONObject typeObject = typesArray.getJSONObject(j).getJSONObject("type");
            Type type = new Type();
            type.setName(typeObject.getString("name"));
            type.setUrl(typeObject.getString("url"));
            types.add(type);
        }
        pokemon.setTypes(types);

        //Obtener las habilidades
        JSONArray abilitiesArray = pokemonObject.getJSONArray("abilities");
        List<Ability> abilities = new ArrayList<>();
        // Iterar sobre el arreglo de habilidades ya que cada habilidad es un objeto
        for (int j = 0; j < abilitiesArray.length(); j++) {
            JSONObject abilityObject = abilitiesArray.getJSONObject(j).getJSONObject("ability");
            Ability ability = new Ability();
            ability.setName(abilityObject.getString("name"));
            ability.setUrl(abilityObject.getString("url"));
            abilities.add(ability);
        }
        pokemon.setAbilities(abilities);
        // Obtener la URL de la imagen del pokemon
        pokemon.setImageUrl(pokemonObject.getJSONObject("sprites").getString("front_default"));

        // Obtener la descripción y las evoluciones
        String speciesUrl = pokemonObject.getJSONObject("species").getString("url");
        String speciesResponse = restTemplate.getForObject(speciesUrl, String.class);
        JSONObject speciesObject = new JSONObject(speciesResponse);
        String description = speciesObject.getJSONArray("flavor_text_entries").getJSONObject(0)
                .getString("flavor_text");
        pokemon.setDescription(description);

        String evolutionChainUrl = speciesObject.getJSONObject("evolution_chain").getString("url");
        String evolutionResponse = restTemplate.getForObject(evolutionChainUrl, String.class);
        JSONObject evolutionObject = new JSONObject(evolutionResponse);

        // Procesar la cadena de evoluciones
        List<Evolution> evolutions = new ArrayList<>();
        JSONObject chain = evolutionObject.getJSONObject("chain");
        while (chain != null) {
            Evolution evolution = new Evolution();
            evolution.setName(chain.getJSONObject("species").getString("name"));
            evolution.setUrl(chain.getJSONObject("species").getString("url"));
            evolutions.add(evolution);

            JSONArray evolvesToArray = chain.getJSONArray("evolves_to");
            // Si la cadena de evoluciones tiene más de un elemento, se toma el primero
            if (evolvesToArray.length() > 0) {
                chain = evolvesToArray.getJSONObject(0);
            } else { // Si no, se termina el ciclo
                chain = null;
            }
        }
        pokemon.setEvolutions(evolutions);

        return pokemon;
    }
}