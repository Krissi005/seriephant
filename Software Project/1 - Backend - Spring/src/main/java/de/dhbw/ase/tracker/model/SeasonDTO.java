package de.dhbw.ase.tracker.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SeasonDTO implements Serializable {
    private Integer seasonNumber;
    private Long serieId;
}
