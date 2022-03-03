package de.dhbw.ase.plugins.persistence.hibernate.episode;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    List<Episode> getEpisodesByUsersEquals(User user);

    @Query(value = "SELECT DISTINCT EPISODE.* FROM EPISODE LEFT OUTER JOIN USER_EPISODE_RATING ON EPISODE.ID = USER_EPISODE_RATING.EPISODE_ID WHERE USER_EPISODE_RATING.USER_ID IS NULL OR USER_EPISODE_RATING.USER_ID!=?1", nativeQuery = true)
    List<Episode> getEpisodesByUsersIsNullOrUsersIsNot(Long userId);

    List<Episode> getEpisodesByTitle(String title);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
