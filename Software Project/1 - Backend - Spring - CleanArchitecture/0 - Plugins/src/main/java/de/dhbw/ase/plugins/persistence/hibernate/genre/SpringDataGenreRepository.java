package de.dhbw.ase.plugins.persistence.hibernate.genre;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface SpringDataGenreRepository extends JpaRepository<Genre, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/

    Genre getById(Long genreId);

    List<Genre> getGenresByTitle(String title);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
