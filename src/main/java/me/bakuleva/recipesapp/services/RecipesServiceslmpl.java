package me.bakuleva.recipesapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
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

@Data
@Service
public class RecipesServiceslmpl implements RecipesServices {
    private final Map<Long, Recipe> recipeMap = new HashMap<>();
    private long counter;
    private final Path path;
    private final ObjectMapper objectMapper;

    public RecipesServiceslmpl(@Value("${application.file.recipes") String path) {
        try {
            this.path = Paths.get(path);
            this.objectMapper = new ObjectMapper();
        } catch (
                InvalidPathException e) {
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
            Map<Long, Recipe> mapFromFile = objectMapper.readValue(file, new TypeReference<Map<Long, Recipe>>() {
            });
            recipeMap.putAll(mapFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToFile(Map<Long,Recipe> recipeMap) {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(recipeMap);
            Files.write(path, bytes);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Recipe add(Recipe recipe) {
        recipeMap.put(this.counter++, recipe);
        writeDataToFile(recipeMap);
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe update(long id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            writeDataToFile(recipeMap);
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe remove(long id) {
        Recipe recipe = recipeMap.remove(id);
        writeDataToFile(recipeMap);
        return recipe;
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipeMap.values());
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
    public void importRecipes(MultipartFile recipes) {
      try {
          Map<Long, Recipe> mapFromRequest = objectMapper.readValue(recipes.getBytes(), new TypeReference<>() {
          });
          writeDataToFile(mapFromRequest);
       } catch (IOException e) {
        e.printStackTrace();
    }

    }
}

