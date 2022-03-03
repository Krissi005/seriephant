package de.dhbw.ase.seriephant.domain.rating;

import de.dhbw.ase.seriephant.domain.user.User;

import java.util.List;

public interface RatingRepository {

    boolean existsById(RatingKey ratingKey);

    /**
     * CREATE
     **/

    Rating save(Rating rating);

    /**
     * READ
     **/

    Rating getById(RatingKey ratingKey);

    List<Rating> getAllWithAvg();

    List<Rating> getByUser(User user);

    List<Rating> getRatingsByUser(Long userId);

    List<Rating> getRatingsForAvg();

    List<Rating> findAllRatingsOfAllEpisodes();

    List<Rating> findAll();

    /** UPDATE **/

    /**
     * DELETE
     **/
}
