package de.dhbw.ase.seriephant.domain.episode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.seriephant.domain.actor.Actor;
import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "episode")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "releaseDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    @Column(name = "episodeNumber")
    private Integer episodeNumber;
    @ManyToOne
    @JsonIgnoreProperties("episodes")
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;
    @ManyToMany(mappedBy = "watchedEpisodes", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties("watchedEpisodes")
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "episode")
    @JsonIgnoreProperties("episode")
    List<Rating> ratings;
    @ManyToMany(mappedBy = "playedInEpisodes", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties("playedInEpisodes")
    private List<Actor> actors = new ArrayList<>();

    public Episode(String title, LocalDate releaseDate, Integer episodeNumber, Season season) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.episodeNumber = episodeNumber;
        this.season = season;
    }

    public Episode(Long id, String title, LocalDate releaseDate, Integer episodeNumber, Season season, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.episodeNumber = episodeNumber;
        this.season = season;
        this.actors = actors;
    }

    @PreRemove
    public void preRemove() {
        this.users.forEach(user -> user.removeWatchedEpisode(this));
    }
}
