package de.dhbw.ase.tracker.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SeasonDTO implements Serializable {
    private Integer seasonNumber;
    private List<Episode> episodes;
}
