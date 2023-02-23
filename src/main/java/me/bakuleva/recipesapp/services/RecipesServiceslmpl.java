package me.bakuleva.recipesapp.services;

import lombok.Data;
import me.bakuleva.recipesapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service
public class RecipesServiceslmpl implements RecipesServices{
    private final Map<Long, Recipe> recipeMap=new HashMap<>();
    private long counter;

    @Override
    public Recipe add(Recipe recipe) {
        recipeMap.put(this.counter++,recipe);
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        return   recipeMap.get(id);
    }

    @Override
    public Recipe update(long id, Recipe recipe) {
        if (recipeMap.containsKey(id)){
            recipeMap.put(id,recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe remove(long id) {
        return recipeMap.remove(id);
    }

    @Override
    public List<Recipe> getAll() {
        return new  ArrayList<>(this.recipeMap.values()) ;
    }
}

