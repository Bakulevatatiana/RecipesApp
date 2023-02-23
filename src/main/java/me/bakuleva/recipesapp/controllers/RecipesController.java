package me.bakuleva.recipesapp.controllers;


import me.bakuleva.recipesapp.model.Recipe;
import me.bakuleva.recipesapp.services.RecipesServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipesController {
    private final RecipesServices recipesServices;

    public RecipesController(RecipesServices recipesServices) {
        this.recipesServices = recipesServices;
    }
    @GetMapping
    public List<Recipe> getAll(){
        return  this.recipesServices.getAll();
    }
    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe){
        return recipesServices.add(recipe);
    }
    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") long id){
        return recipesServices.get(id);

    }
    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable("id") long id,@RequestBody Recipe recipe){
        return recipesServices.update(id, recipe);}

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") long id){
        return recipesServices.remove(id);
    }
}
