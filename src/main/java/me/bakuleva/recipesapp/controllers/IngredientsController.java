package me.bakuleva.recipesapp.controllers;

import me.bakuleva.recipesapp.model.Ingredient;
import me.bakuleva.recipesapp.services.IngredientsServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")

public class IngredientsController {
    private final IngredientsServices ingredientsServices;

    public IngredientsController(IngredientsServices ingredientsServices) {
        this.ingredientsServices = ingredientsServices;

    }

   @GetMapping
    public List<Ingredient> getAll(){
        return  this.ingredientsServices.getAll();}
   @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient){
        return ingredientsServices.add(ingredient);
    }
    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") long id){
        return ingredientsServices.get(id);

    }

    @PostMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable("id") long id,@RequestBody Ingredient ingredient){
        return ingredientsServices.update(id, ingredient);}

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") long id){
        return ingredientsServices.remove(id);
    }

}
