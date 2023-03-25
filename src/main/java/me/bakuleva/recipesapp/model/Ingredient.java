
package me.bakuleva.recipesapp.model;

import lombok.Data;

@Data
public class Ingredient {
    private  String name;
    private  int weight;
    private  String measureUnit;

    public Ingredient() {

    }

    @Override
    public String toString() {
        return  name + " - " + weight +
                " " + measureUnit ;
    }
}


