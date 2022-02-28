package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.domain.rating.RatingKey;
import de.dhbw.ase.seriephant.domain.rating.RatingRepository;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

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
        if (rating != null) {
            if (rating.getId() != null && this.ratingRepository.existsById(rating.getId())) {
                return this.createRatingByUserAndEpisode(rating, rating.getId().getUserId(), rating.getId().getEpisodeId());
            } else if (rating.getUser() != null && rating.getUser().getId() != null &&
                    rating.getEpisode() != null && rating.getEpisode().getId() != null
                    && this.ratingRepository.existsById(new RatingKey(rating.getUser().getId(), rating.getEpisode().getId()))) {
                return this.createRatingByUserAndEpisode(rating, rating.getUser().getId(), rating.getEpisode().getId());
            }
            throw new ValidationException("Episode is not watched yet.");
        }
        throw new ValidationException("Rating is not valid.");
    }

    private Rating createRatingByUserAndEpisode(Rating rating, Long userId, Long episodeId) throws ValidationException {
        if (this.userRepository.existsById(userId)) {
            rating.setUser(this.userRepository.getById(userId));
            if (this.episodeRepository.existsById(episodeId)) {
                rating.setEpisode(this.episodeRepository.getById(episodeId));
                return this.ratingRepository.save(rating);
            }
            throw new ValidationException("Id of Episode is not known.");
        }
        throw new ValidationException("Id of User is not known.");
    }

    public Rating saveEpisodeRating(Long userId, Long episodeId, Integer rating) throws ValidationException {
        if (this.ratingRepository.existsById(new RatingKey(userId, episodeId))) {
            Rating episodeRating = new Rating(new RatingKey(userId, episodeId), this.userRepository.getById(userId), this.episodeRepository.getById(episodeId), rating);
            return this.ratingRepository.save(episodeRating);
        }
        throw new ValidationException("Episode is not watched yet.");
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
    public Rating updateEpisodeRating(Long userId, Long episodeId, Integer rating) throws ValidationException {
        if (this.ratingRepository.existsById(new RatingKey(userId, episodeId))) {
            Rating episodeRating = this.ratingRepository.getById(new RatingKey(userId, episodeId));
            episodeRating.setRating(rating);
            this.ratingRepository.save(episodeRating);
        }
        throw new ValidationException("Episode is not watched yet.");
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
        if (this.ratingRepository.existsById(new RatingKey(userId, episodeId))) {
            Rating rating = this.ratingRepository.getById(new RatingKey(userId, episodeId));
            rating.setRating(null);
            this.ratingRepository.save(rating);
        } else {
            throw new ValidationException("Id of Rating is not known.");
        }
    }
    /************************************************************************************************************************************/
}
