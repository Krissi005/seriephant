package de.dhbw.ase.tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JsonIgnoreProperties("users")
    @JoinTable(
            name = "user_episode_ranking",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "episode_id", referencedColumnName = "id") }
    )
    List<Episode> watchedEpisodes = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    List<EpisodeRating> ratings;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void watchEpisode(Episode episode) {
        watchedEpisodes.add(episode);
    }

    public void removeWatchedEpisode(Episode episode) {
        watchedEpisodes.remove(episode);
    }
}
