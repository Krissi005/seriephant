package de.dhbw.ase.seriephant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="user_episode_ranking")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EpisodeRating {

    @EmbeddedId
    EpisodeRatingKey id;

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

    Integer rating;

    public EpisodeRating(User user, Episode episode, Integer rating){
        this.id = new EpisodeRatingKey(user.getId(), episode.getId());
        this.user = user;
        this.episode = episode;
        this.rating = rating;
    }
}
