package de.dhbw.ase.seriephant.repository;

import de.dhbw.ase.seriephant.model.EpisodeRating;
import de.dhbw.ase.seriephant.model.EpisodeRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface EpisodeRatingRepository extends JpaRepository<EpisodeRating, EpisodeRatingKey> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/

    /** UPDATE **/

    /**
     * DELETE
     **/
}
