package de.dhbw.ase.seriephant.domain.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class RatingKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "episode_id")
    Long episodeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        RatingKey ratingKey = (RatingKey) o;
        return Objects.equals(this.userId, ratingKey.userId) && Objects.equals(this.episodeId, ratingKey.episodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.episodeId);
    }
}
