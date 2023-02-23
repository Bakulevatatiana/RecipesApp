package me.bakuleva.recipesapp.model;

import lombok.Data;

import java.util.List;
@Data
public class Recipe {

    private final String name;
    private final int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;
}



