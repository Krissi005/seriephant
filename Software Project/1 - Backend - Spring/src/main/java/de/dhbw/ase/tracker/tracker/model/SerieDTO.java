package de.dhbw.ase.tracker.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SerieDTO implements Serializable {
    private String title;
    private String description;
    private Long genreId;
}
