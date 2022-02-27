package de.dhbw.ase.seriephant.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SerieDTO implements Serializable {
    private String title;
    private String description;
    private Integer releaseYear;
    private Long genreId;
}
