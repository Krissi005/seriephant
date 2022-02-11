package de.dhbw.ase.tracker.tracker.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EpisodeDTO implements Serializable {
    private String title;
    private Date releaseDate;
    private Integer episodeNumber;
    private Long seasonId;
}
