package de.dhbw.ase.plugins.persistence.hibernate.rating;

import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.domain.rating.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SpringDataRatingRepository extends JpaRepository<Rating, RatingKey> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Rating getById(RatingKey ratingKey);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
