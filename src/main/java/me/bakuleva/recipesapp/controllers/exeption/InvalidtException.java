package me.bakuleva.recipesapp.controllers.exeption;

public class InvalidtException extends RuntimeException{
    public InvalidtException(String message) {
        super(message);
    }
}
