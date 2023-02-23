package me.bakuleva.recipesapp.services;

import me.bakuleva.recipesapp.model.Ingredient;
import me.bakuleva.recipesapp.model.Recipe;

public interface IngredientsServies {
    Ingredient add(Ingredient ingredient);
    Ingredient get(long id);
    Ingredient update(long id,Ingredient ingredient);
    Ingredient remove( long id);
}
