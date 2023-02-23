package me.bakuleva.recipesapp.services;

import lombok.Data;
import me.bakuleva.recipesapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class RecipesServices {
    private final Map<Integer, Recipe> recipeMap=new HashMap<>();

}
