package de.dhbw.ase.seriephant.repository;

import de.dhbw.ase.seriephant.model.Season;
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
