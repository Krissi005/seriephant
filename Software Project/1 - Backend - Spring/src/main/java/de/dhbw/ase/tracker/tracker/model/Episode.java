package de.dhbw.ase.tracker.tracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    public Episode(String title, int episodeNumber, Season season){
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.season = season;
    }
}
