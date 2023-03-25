package me.bakuleva.recipesapp.record;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RecordInfo {
    private final String name;
    private final String projectTitle;
    private final LocalDate projectDate;
    private final String description;
}
