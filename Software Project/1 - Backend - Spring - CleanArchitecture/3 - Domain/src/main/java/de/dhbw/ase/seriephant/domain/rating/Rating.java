package de.dhbw.ase.seriephant.domain.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "user_episode_rating")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Rating {

    @EmbeddedId
    RatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JsonIgnoreProperties("ratings")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("episodeId")
    @JsonIgnoreProperties("ratings")
    @JoinColumn(name = "episode_id")
    Episode episode;

    Double rating;

    public Rating(User user, Episode episode, Double rating) {
        this.id = new RatingKey(user.getId(), episode.getId());
        this.user = user;
        this.episode = episode;
        this.rating = rating;
    }
}
