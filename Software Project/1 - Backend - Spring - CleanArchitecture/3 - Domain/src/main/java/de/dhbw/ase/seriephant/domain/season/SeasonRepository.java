package de.dhbw.ase.seriephant.domain.season;

import java.util.List;

public interface SeasonRepository {

    boolean existsById(Long seasonId);

    /**
     * CREATE
     **/

    Season save(Season season);

    /**
     * READ
     **/

    Season getById(Long seasonId);

    List<Season> findAll();

    /** UPDATE **/

    /**
     * DELETE
     **/

    void deleteById(Long seasonId);
}
