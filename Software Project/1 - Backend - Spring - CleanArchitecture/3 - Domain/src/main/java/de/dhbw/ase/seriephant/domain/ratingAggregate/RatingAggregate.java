package de.dhbw.ase.seriephant.domain.ratingAggregate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.rating.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class RatingAggregate {

    @JsonIgnoreProperties({"ratings", "users"})
    Episode episode;

    @JsonIgnoreProperties({"episode", "user"})
    List<Rating> ratings;

    public RatingAggregate(Episode episode, List<Rating> ratings) {
        this.episode = episode;
        this.ratings = ratings;
    }

    public Episode getEpisode() {
        return this.episode;
    }

    public List<Rating> getRatings() {
        return this.ratings;
    }

    public Double getAverageRating() {
        return this.calculateAverage();
    }

    private Double calculateAverage() {
        double sumRating = 0.0;
        int number = 0;
        for (Double ratingValue : this.ratings.stream().map(Rating::getRatingValue).collect(Collectors.toList())) {
            if (ratingValue != null) {
                sumRating += ratingValue;
                number++;
            }
        }
        return number == 0 ? null : sumRating / number;
    }

    public Integer getNumberOfRatings() {
        return this.ratings.size();
    }

}
