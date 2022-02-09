package de.dhbw.ase.tracker.tracker.repository;

import de.dhbw.ase.tracker.tracker.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SerieRepository extends JpaRepository<Serie, Long> {
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
