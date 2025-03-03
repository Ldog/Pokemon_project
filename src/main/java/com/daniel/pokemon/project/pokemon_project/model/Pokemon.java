package com.daniel.pokemon.project.pokemon_project.model;

import java.util.List;

public class Pokemon {

    private String name;
    private List<Type> types;
    private List<Ability> abilities;
    private int weight;
    private String imageUrl;
    private String description;
    private List<Evolution> evolutions;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Evolution> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(List<Evolution> evolutions) {
        this.evolutions = evolutions;
    }

}
