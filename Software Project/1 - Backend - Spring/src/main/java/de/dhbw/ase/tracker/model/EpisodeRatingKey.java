package de.dhbw.ase.tracker.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
public class EpisodeRatingKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "episode_id")
    Long episodeId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}
