package me.bakuleva.recipesapp.services;

import me.bakuleva.recipesapp.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IngredientsServiceslmpl implements IngredientsServices{
    private final Map<Long,Ingredient> ingredientMap= new HashMap<>();
    private long counter=0;
    @Override
    public Ingredient add(Ingredient ingredient) {
        return ingredientMap.put(counter++,ingredient);
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {


            return ingredientMap.put(id, ingredient);
        }
       throw new RuntimeException ("Ингредиент для замены не найден");
    }


    @Override
    public Ingredient remove(long id) {
        return ingredientMap.remove(id);
    }
    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values()) ;
    }
}


