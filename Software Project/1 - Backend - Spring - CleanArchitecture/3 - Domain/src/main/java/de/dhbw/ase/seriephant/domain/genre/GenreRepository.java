package de.dhbw.ase.seriephant.domain.genre;

import java.util.List;

public interface GenreRepository {

    boolean existsById(Long genreId);

    /**
     * CREATE
     **/

    Genre save(Genre genre);

    /**
     * READ
     **/

    Genre getById(Long genreId);

    List<Genre> getGenresByTitle(String title);

    List<Genre> findAll();

    /**
     * UPDATE
     **/

    /**
     * DELETE
     **/

    void deleteById(Long genreId);
}
