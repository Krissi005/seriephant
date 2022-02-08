package de.dhbw.ase.tracker.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="episode")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "episodeNumber")
    private Integer episodeNumber;

    public Episode(String title, int episodeNumber){
        this.title = title;
        this.episodeNumber = episodeNumber;
    }

    public void updateFromDTO(EpisodeDTO episodeDTO) {
        if (episodeDTO.getTitle() != null) {
            this.title = episodeDTO.getTitle();
        }
        if (episodeDTO.getEpisodeNumber() != null) {
            this.episodeNumber = episodeDTO.getEpisodeNumber();
        }
    }
}
