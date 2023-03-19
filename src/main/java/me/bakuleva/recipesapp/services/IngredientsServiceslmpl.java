package me.bakuleva.recipesapp.services;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.bakuleva.recipesapp.model.Ingredient;
import me.bakuleva.recipesapp.model.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IngredientsServiceslmpl implements IngredientsServices{
    private final Map<Long,Ingredient> ingredientMap= new HashMap<>();
    private long counter=0;

    private final Path path;
    private final ObjectMapper objectMapper;
    public IngredientsServiceslmpl(@Value("${application.file.ingredients")String path){
       try {
           this.path= Paths.get(path);
           this.objectMapper= new ObjectMapper();
       } catch (InvalidPathException e) {
           e.printStackTrace();
           throw e;
       }
    }

    @PostConstruct
    public void init() {
        readDataFromFile();


    }

    private void readDataFromFile() {
        try {
            byte[] file = Files.readAllBytes(path);
            Map<Long, Ingredient> mapFromFile = objectMapper.readValue(file, new TypeReference<Map<Long, Ingredient>>() {
            });
            ingredientMap.putAll(mapFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeDataToFile(Map<Long, Ingredient> ingredientMap){
        try {
            byte [] bytes=objectMapper.writeValueAsBytes(ingredientMap);
            Files.write(path,bytes);

        }catch (JsonProcessingException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        Ingredient newIngredient= ingredientMap.put(counter++,ingredient);
        writeDataToFile(ingredientMap);
        return newIngredient;
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            Ingredient newIngredient= ingredientMap.put(id, ingredient);
            writeDataToFile(ingredientMap);
            return  newIngredient;
        }
       throw new RuntimeException ("Ингредиент для замены не найден");
    }


    @Override
    public Ingredient remove(long id) {
        Ingredient ingredient= ingredientMap.remove(id);
        writeDataToFile(ingredientMap);
         return ingredient;
    }
    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values()) ;
    }

    @Override
    public byte[] getAllInBytes() {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void importRecipes(MultipartFile ingredient) {
        try {
            Map<Long, Ingredient> mapFromRequest = objectMapper.readValue(ingredient.getBytes(), new TypeReference<>() {
            });
            writeDataToFile(mapFromRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }




