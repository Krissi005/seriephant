package de.dhbw.ase.seriephant.domain.rating;

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

    List<Rating> findAll();

    /** UPDATE **/

    /**
     * DELETE
     **/
}
