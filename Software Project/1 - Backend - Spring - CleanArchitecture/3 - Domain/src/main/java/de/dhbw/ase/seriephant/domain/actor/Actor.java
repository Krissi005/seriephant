package de.dhbw.ase.seriephant.domain.actor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "actor")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("actors")
    @JoinTable(
            name = "actor_episode",
            joinColumns = {@JoinColumn(name = "actor_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "episode_id", referencedColumnName = "id")}
    )
    List<Episode> playedInEpisodes = new ArrayList<>();

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actor(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void playInEpisode(Episode episode) {
        this.playedInEpisodes.add(episode);
    }

    public void removePlayedInEpisode(Episode episode) {
        this.playedInEpisodes.remove(episode);
    }
}
