package de.dhbw.ase.seriephant.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.rating.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("users")
    @JoinTable(
            name = "user_episode_rating",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "episode_id", referencedColumnName = "id")}
    )
    List<Episode> watchedEpisodes = new ArrayList<>();
    @Transient
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    List<Rating> ratings;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String firstName, String lastName, List<Episode> watchedEpisodes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.watchedEpisodes = watchedEpisodes;
    }

    public void watchEpisode(Episode episode) {
        this.watchedEpisodes.add(episode);
    }

    public void removeWatchedEpisode(Episode episode) {
        this.watchedEpisodes.remove(episode);
    }
}
