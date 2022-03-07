package de.dhbw.ase.seriephant.plugins.persistence.hibernate.season;

import de.dhbw.ase.seriephant.domain.season.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SpringDataSeasonRepository extends JpaRepository<Season, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Season getById(Long seasonId);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
