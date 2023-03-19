package me.bakuleva.recipesapp.services;

import me.bakuleva.recipesapp.model.Ingredient;
import me.bakuleva.recipesapp.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecipesServices {
    Recipe add(Recipe recipe);
    Recipe get(long id);
    Recipe update(long id, Recipe recipe);
    Recipe remove( long id);

    List<Recipe> getAll();

    byte [] getAllInBytes();

    void importRecipes(MultipartFile recipes);
}
