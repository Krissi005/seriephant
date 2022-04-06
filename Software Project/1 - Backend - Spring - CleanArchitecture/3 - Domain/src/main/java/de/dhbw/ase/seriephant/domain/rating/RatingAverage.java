package de.dhbw.ase.seriephant.domain.rating;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RatingAverage {

    @Column(name = "episode_id")
    private Long episodeId;

    @Column(name = "episode_title")
    private String episodeTitle;

    @Column(name = "average_rating_value")
    private Double averageRatingValue;

    @Column(name = "number_of_ratings")
    private Integer numberOfRatings;

    public RatingAverage() {

    }

    public RatingAverage(Long episodeId, String episodeTitle, Double averageRatingValue) {
        this(episodeId, episodeTitle, averageRatingValue, null);
    }

    public RatingAverage(Long episodeId, String episodeTitle, Double averageRatingValue, Integer numberOfRatings) {
        this.episodeId = episodeId;
        this.episodeTitle = episodeTitle;
        this.averageRatingValue = averageRatingValue;
        this.numberOfRatings = numberOfRatings;
    }
}
