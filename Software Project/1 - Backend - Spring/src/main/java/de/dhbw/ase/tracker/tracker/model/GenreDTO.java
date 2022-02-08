package de.dhbw.ase.tracker.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class GenreDTO implements Serializable {
    private String title;
    private String description;
}
