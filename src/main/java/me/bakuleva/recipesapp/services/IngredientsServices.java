package me.bakuleva.recipesapp.services;

import me.bakuleva.recipesapp.model.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IngredientsServices {
    Ingredient add(Ingredient ingredient);
    Ingredient get(long id);
    Ingredient update(long id,Ingredient ingredient);
    Ingredient remove( long id);

    List<Ingredient> getAll();

    byte[] getAllInBytes();


    void importIngredients(MultipartFile ingredients);
}
