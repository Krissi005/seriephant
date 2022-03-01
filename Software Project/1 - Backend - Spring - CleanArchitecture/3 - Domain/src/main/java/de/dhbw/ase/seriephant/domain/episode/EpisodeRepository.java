package de.dhbw.ase.seriephant.domain.episode;

import de.dhbw.ase.seriephant.domain.user.User;

import java.util.List;

public interface EpisodeRepository {

    boolean existsById(Long episodeId);

    /**
     * CREATE
     **/

    Episode save(Episode episodeToCreate);

    /**
     * READ
     **/

    Episode getById(Long episodeId);

    List<Episode> getEpisodesByUsersEquals(User byId);

    List<Episode> getEpisodesByTitle(String title);

    List<Episode> findAll();

    /**
     * UPDATE
     **/

    /**
     * DELETE
     **/

    void deleteById(Long episodeId);
}
