package de.dhbw.ase.tracker.repository;

import de.dhbw.ase.tracker.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    List<Episode> getEpisodesByTitle(String title);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
