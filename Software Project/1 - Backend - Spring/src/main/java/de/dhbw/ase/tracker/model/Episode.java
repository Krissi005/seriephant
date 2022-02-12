package de.dhbw.ase.tracker.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "episode")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "releaseDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date releaseDate;
    @Column(name = "episodeNumber")
    private Integer episodeNumber;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;
    @ManyToMany(mappedBy = "watchedEpisodes", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties("watchedEpisodes")
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "episode")
    @JsonBackReference
    List<EpisodeRating> ratings;
    @ManyToMany(mappedBy = "playedInEpisodes", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties("playedInEpisodes")
    private List<Actor> actors = new ArrayList<>();

    public Episode(String title, Date releaseDate, Integer episodeNumber, Season season){
        this.title = title;
        this.releaseDate = releaseDate;
        this.episodeNumber = episodeNumber;
        this.season = season;
    }

    @PreRemove
    public void preRemove() {
        users.forEach(user -> user.removeWatchedEpisode(this));
    }
}
