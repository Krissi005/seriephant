package de.dhbw.ase.tracker.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GenreDTO implements Serializable {
    private String title;
    private String description;
}
