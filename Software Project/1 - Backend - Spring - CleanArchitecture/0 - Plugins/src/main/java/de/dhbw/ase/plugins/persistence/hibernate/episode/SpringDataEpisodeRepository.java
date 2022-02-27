package de.dhbw.ase.plugins.persistence.hibernate.episode;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpringDataEpisodeRepository extends JpaRepository<Episode, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Episode getById(Long episodeId);

    List<Episode> getEpisodesByTitle(String title);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
