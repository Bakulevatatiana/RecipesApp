package me.bakuleva.recipesapp.model;

import lombok.Data;

import java.util.List;
@Data
public class Recipe {

    private  String name;
    private  int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;

    public Recipe() {

    }
}



