package de.dhbw.ase.plugins.persistence.hibernate.serie;

import de.dhbw.ase.seriephant.domain.serie.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface SpringDataSerieRepository extends JpaRepository<Serie, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Serie getById(Long serieId);

    List<Serie> getSeriesByTitleAndAndReleaseYear(String title, Integer releaseYear);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
