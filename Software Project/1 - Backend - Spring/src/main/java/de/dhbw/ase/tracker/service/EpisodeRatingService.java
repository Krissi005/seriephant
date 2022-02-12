package de.dhbw.ase.tracker.service;

import de.dhbw.ase.tracker.helper.Checker;
import de.dhbw.ase.tracker.model.Episode;
import de.dhbw.ase.tracker.model.EpisodeRating;
import de.dhbw.ase.tracker.model.EpisodeRatingKey;
import de.dhbw.ase.tracker.model.User;
import de.dhbw.ase.tracker.repository.EpisodeRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class EpisodeRatingService {

    @Autowired
    EpisodeRatingRepository episodeRatingRepository;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public EpisodeRating saveEpisodeRating(Long userId, Long episodeId, Integer rating) throws ValidationException {
        if (episodeRatingRepository.existsById(new EpisodeRatingKey(userId, episodeId))) {
            User user = Checker.getUserById(userId);
            Episode episode = Checker.getEpisodeById(episodeId);
            EpisodeRating episodeRating = new EpisodeRating(new EpisodeRatingKey(userId, episodeId), user, episode, rating);
            episodeRatingRepository.save(episodeRating);
            return episodeRating;
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
    public List<EpisodeRating> getAllEpisodeRatings() {
        return episodeRatingRepository.findAll();
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
    public EpisodeRating updateEpisodeRating(Long userId, Long episodeId, Integer rating) throws ValidationException {
        if (episodeRatingRepository.existsById(new EpisodeRatingKey(userId, episodeId))) {
            EpisodeRating episodeRating = episodeRatingRepository.findById(new EpisodeRatingKey(userId, episodeId)).get();
            episodeRating.setRating(rating);
            episodeRatingRepository.save(episodeRating);
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
        if (episodeRatingRepository.existsById(new EpisodeRatingKey(userId, episodeId))) {
            EpisodeRating episodeRating = episodeRatingRepository.findById(new EpisodeRatingKey(userId, episodeId)).get();
            episodeRating.setRating(null);
            episodeRatingRepository.save(episodeRating);
        }
        throw new ValidationException("Id is not known.");
    }
    /************************************************************************************************************************************/
}
