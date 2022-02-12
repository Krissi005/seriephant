package de.dhbw.ase.tracker.repository;

import de.dhbw.ase.tracker.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    List<Genre> getGenresByTitle(String title);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
