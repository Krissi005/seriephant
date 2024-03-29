package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.domain.rating.RatingAverage;
import de.dhbw.ase.seriephant.domain.rating.RatingKey;
import de.dhbw.ase.seriephant.domain.rating.RatingRepository;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingApplicationService {

    private final UserRepository userRepository;
    private final EpisodeRepository episodeRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingApplicationService(UserRepository userRepository, EpisodeRepository episodeRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.episodeRepository = episodeRepository;
        this.ratingRepository = ratingRepository;
    }

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Rating saveEpisodeRating(Rating rating) throws ValidationException {
        if (rating == null || rating.getRatingValue() == null || rating.getRatingValue() < 0 || rating.getRatingValue() > 10) {
            throw new ValidationException("Rating is not valid.");
        }

        if (rating.getId() != null && rating.getId().getUserId() != null && rating.getId().getEpisodeId() != null) {
            return this.saveEpisodeRating(rating.getUser().getId(), rating.getEpisode().getId(), rating.getRatingValue());
        } else if (rating.getUser() != null && rating.getUser().getId() != null) {
            if (rating.getEpisode() != null && rating.getEpisode().getId() != null) {
                return this.saveEpisodeRating(rating.getUser().getId(), rating.getEpisode().getId(), rating.getRatingValue());
            }
            throw new ValidationException("Id of Episode is not known.");
        }
        throw new ValidationException("Id of User is not known.");
    }

    public Rating saveEpisodeRating(Long userId, Long episodeId, Double rating) throws ValidationException {
        if (!this.userRepository.existsById(userId)) {
            throw new ValidationException("Id of User is not known.");
        }

        if (!this.episodeRepository.existsById(episodeId)) {
            throw new ValidationException("Id of Episode is not known.");
        }

        if (!this.ratingRepository.existsById(new RatingKey(userId, episodeId))) {
            throw new ValidationException("Episode is not watched yet.");
        }

        Rating episodeRating = new Rating(new RatingKey(userId, episodeId), this.userRepository.getById(userId), this.episodeRepository.getById(episodeId), rating);
        return this.ratingRepository.save(episodeRating);
    }

    /**
     *
     ***********************************************************************************************************************************/
    /*
         _____                _
        |  __ \              | |
        | |__) |___  __ _  __| |
        |  _  // _ \/ _` |/ _` |
        | | \ \  __/ (_| | (_| |
        |_|  \_\___|\__,_|\__,_|
    */
    public Rating getRatingById(RatingKey ratingId) throws ValidationException {
        if (this.ratingRepository.existsById(ratingId)) {
            return this.ratingRepository.getById(ratingId);
        }
        throw new ValidationException("Id of Rating is not known.");
    }

    public Rating getRatingByRating(Rating rating) throws ValidationException {
        RatingKey ratingKey = new RatingKey(rating.getUser().getId(), rating.getEpisode().getId());
        if (this.ratingRepository.existsById(ratingKey)) {
            return this.ratingRepository.getById(ratingKey);
        }
        rating.setRatingValue(null);
        return rating;
    }

    public List<Rating> getRatingByUserId(Long userId) throws ValidationException {
        if (this.userRepository.existsById(userId)) {
            return this.ratingRepository.getByUser(this.userRepository.getById(userId));
        }
        throw new ValidationException("User is not valid.");
    }

    public List<RatingAverage> getRatingsNotByUser(Long userId) throws ValidationException {
        if (!this.userRepository.existsById(userId)) {
            throw new ValidationException("User is not valid.");
        }
        return this.getRatings(userId, Boolean.FALSE);

    }

    public List<RatingAverage> getRatingsByUser(Long userId) throws ValidationException {
        if (!this.userRepository.existsById(userId)) {
            throw new ValidationException("User is not valid.");
        }
        return this.getRatings(userId, Boolean.TRUE);
    }

    private List<RatingAverage> getRatings(Long userId, Boolean bool) {
        List<RatingAverage> unwatchedRatings = new ArrayList<>();
        List<Rating> ratings = this.ratingRepository.findAll();
        List<Episode> episodes = this.episodeRepository.findAll();
        for (Episode episode : episodes) {
            Double sumRating = 0.0;
            int number = 0;
            boolean user = false;
            for (Rating rating : ratings) {
                if (episode.getId().equals(rating.getEpisode().getId())) {
                    if (rating.getRatingValue() != null) {
                        sumRating += rating.getRatingValue();
                        number++;
                    }
                    if (rating.getUser().getId().equals(userId)) {
                        user = true;
                    }
                }
            }
            if (bool.equals(user)) {
                unwatchedRatings.add(new RatingAverage(episode.getId(), number == 0 ? null : sumRating / number, number));
            }
        }
        return unwatchedRatings;
    }

    public List<RatingAverage> getAllEpisodesRatings() {
        List<RatingAverage> allEpisodesWithRatings = new ArrayList<>();
        List<Rating> ratings = this.ratingRepository.findAll();
        List<Episode> episodes = this.episodeRepository.findAll();
        for (Episode episode : episodes) {
            Double sumRating = 0.0;
            int number = 0;
            for (Rating rating : ratings) {
                if (episode.getId().equals(rating.getEpisode().getId()) && rating.getRatingValue() != null) {
                    sumRating += rating.getRatingValue();
                    number++;
                }
            }
            allEpisodesWithRatings.add(new RatingAverage(episode.getId(), number == 0 ? null : sumRating / number, number));
        }
        return allEpisodesWithRatings;
    }

    public List<RatingAverage> getAllEpisodesWithRatings() {
        return this.ratingRepository.getAllAverages();
    }

    public List<RatingAverage> getUnwatchedEpisodesWithRatings(Long userId) {
        return this.getRatingAggregatesByWatchedFilter(userId, false);
    }

    public List<RatingAverage> getWatchedEpisodesWithRatings(Long userId) {
        return this.getRatingAggregatesByWatchedFilter(userId, true);
    }

    private List<RatingAverage> getRatingAggregatesByWatchedFilter(Long userId, Boolean bool) {
        List<Long> watchedEpisodes = this.episodeRepository.getEpisodesByUsersEquals(userId).stream().map(Episode::getId).collect(Collectors.toList());
        List<RatingAverage> allEpisodesWithRatings = new ArrayList<>();
        List<RatingAverage> ratingAverages = this.ratingRepository.getAllAverages();
        for (RatingAverage ratingAverage : ratingAverages) {
            if (bool.equals(watchedEpisodes.contains(ratingAverage.getEpisodeId()))) {
                allEpisodesWithRatings.add(ratingAverage);
            }
        }
        return allEpisodesWithRatings;
    }

    public List<Rating> getAllRatingsOfEpisode(Long episodeId) {
        return this.ratingRepository.getAllRatingsOfEpisode(episodeId);
    }

    public List<Rating> getAllEpisodeRatings() {
        return this.ratingRepository.findAll();
    }

    /************************************************************************************************************************************/
    /*
         _    _           _       _
        | |  | |         | |     | |
        | |  | |_ __   __| | __ _| |_ ___
        | |  | | '_ \ / _` |/ _` | __/ _ \
        | |__| | |_) | (_| | (_| | ||  __/
        \____/| .__/ \__,_|\__,_|\__\___|
              | |
              |_|
    */
    public Rating updateEpisodeRating(Long userId, Long episodeId, Double rating) throws ValidationException {
        return this.saveEpisodeRating(userId, episodeId, rating);
    }

    /************************************************************************************************************************************/
    /*
         _____       _      _
        |  __ \     | |    | |
        | |  | | ___| | ___| |_ ___
        | |  | |/ _ \ |/ _ \ __/ _ \
        | |__| |  __/ |  __/ ||  __/
        |_____/ \___|_|\___|\__\___|
    */
    public void deleteEpisodeRating(Long userId, Long episodeId) throws ValidationException {
        if (!this.ratingRepository.existsById(new RatingKey(userId, episodeId))) {
            throw new ValidationException("Id of Rating is not known.");
        }
        Rating rating = this.ratingRepository.getById(new RatingKey(userId, episodeId));
        rating.setRatingValue(null);
        this.ratingRepository.save(rating);

    }

    /************************************************************************************************************************************/
}
