package de.dhbw.ase.seriephant.domain.rating;

import lombok.Getter;

@Getter
public class RatingAverage {

    private Long episodeId;

    private Double averageRatingValue;

    private Integer numberOfRatings;

    public RatingAverage(Long episodeId, Double averageRatingValue) {
        this(episodeId, averageRatingValue, null);
    }

    public RatingAverage(Long episodeId, Double averageRatingValue, Integer numberOfRatings) {
        this.episodeId = episodeId;
        this.averageRatingValue = averageRatingValue;
        this.numberOfRatings = numberOfRatings;
    }
}
