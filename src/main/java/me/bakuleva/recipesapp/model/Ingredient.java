
package me.bakuleva.recipesapp.model;

import lombok.Data;

@Data
public class Ingredient {
    private final String name;
    private final int weight;
    private final String measureUnit;
}

