package de.dhbw.ase.seriephant.domain.serie;

import java.util.List;

public interface SerieRepository {
    boolean existsById(Long serieId);

    /**
     * CREATE
     **/
    Serie save(Serie serie);

    /**
     * READ
     **/
    Serie getById(Long serieId);

    List<Serie> getSeriesByTitleAndAndReleaseYear(String title, Integer releaseYear);

    List<Serie> findAll();

    /** UPDATE **/

    /**
     * DELETE
     **/

    void deleteById(Long serieId);
}
