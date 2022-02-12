package de.dhbw.ase.tracker.repository;

import de.dhbw.ase.tracker.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SeasonRepository extends JpaRepository<Season, Long> {
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
