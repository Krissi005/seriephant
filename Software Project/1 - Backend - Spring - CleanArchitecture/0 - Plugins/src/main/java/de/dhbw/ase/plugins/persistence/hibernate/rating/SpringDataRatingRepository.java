package de.dhbw.ase.plugins.persistence.hibernate.rating;

import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.domain.rating.RatingKey;
import de.dhbw.ase.seriephant.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface SpringDataRatingRepository extends JpaRepository<Rating, RatingKey> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Rating getById(RatingKey ratingKey);

    @Query(value = "SELECT EPISODE.ID AS USER_ID, EPISODE.ID AS EPISODE_ID, USER_EPISODE_RATING.RATING FROM EPISODE LEFT OUTER JOIN USER_EPISODE_RATING ON EPISODE.ID = USER_EPISODE_RATING.EPISODE_ID", nativeQuery = true)
    List<Rating> getAllWithAvg();

    List<Rating> getByUser(User user);

    @Query(value = "SELECT DINSTINCT USER_EPISODE_RATING.*" +
            "FROM EPISODE episode " +
            "LEFT OUTER JOIN USER_EPISODE_RATING rating ON episode.ID = rating.EPISODE_ID " +
            "WHERE rating.USER_ID IS NULL " +
            "OR rating.USER_ID != ?1 " +
            "GROUP BY episode.ID", nativeQuery = true)
    List<Rating> getRatingsByUser(Long userId);

    @Query(value = "SELECT USER_EPISODE_RATING.*" +
            "FROM EPISODE episode " +
            "LEFT OUTER JOIN USER_EPISODE_RATING rating ON episode.ID = rating.EPISODE_ID ", nativeQuery = true)
    List<Rating> getRatingsForAvg();


    /** UPDATE **/

    /**
     * DELETE
     **/
}
