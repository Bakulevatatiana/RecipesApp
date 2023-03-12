package me.bakuleva.recipesapp.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.bakuleva.recipesapp.model.Recipe;
import me.bakuleva.recipesapp.services.RecipesServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты",description = "Рецепты с подробным описанием.")
public class RecipesController {
    private final RecipesServices recipesServices;

    public RecipesController(RecipesServices recipesServices) {
        this.recipesServices = recipesServices;
    }
    @GetMapping
    @Operation(description = "Поиск всех рецептов.")
    public List<Recipe> getAll(){
        return  this.recipesServices.getAll();
    }
    @PostMapping
    @Operation(description = "Добавление рецепта.")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe){
        if (StringUtils.isBlank(recipe.getName())){
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым.");
        }

        return ResponseEntity.ok(recipesServices.add(recipe));
    }
    @GetMapping("/{id}")
    @Operation(description = "Получение рецепта.")
    public Recipe getRecipe(@PathVariable("id") long id){
        return recipesServices.get(id);

    }
    @PutMapping("/{id}")
    @Operation(description = "Замена рецептов.")
    public Recipe updateRecipe(@PathVariable("id") long id,@RequestBody Recipe recipe){
        return recipesServices.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление рецептов.")
    public Recipe deleteRecipe(@PathVariable("id") long id){
        return recipesServices.remove(id);
    }
}
