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
    @Transient
    @ManyToMany(mappedBy = "actors", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties("actors")
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

    @PreRemove
    public void preRemove() {
        this.playedInEpisodes.forEach(episode -> episode.removeActor(this));
    }
}
