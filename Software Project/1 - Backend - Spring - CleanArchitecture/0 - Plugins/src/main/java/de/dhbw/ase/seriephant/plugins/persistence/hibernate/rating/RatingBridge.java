package de.dhbw.ase.seriephant.plugins.persistence.hibernate.rating;

import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.domain.rating.RatingKey;
import de.dhbw.ase.seriephant.domain.rating.RatingRepository;
import de.dhbw.ase.seriephant.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingBridge implements RatingRepository {
    private SpringDataRatingRepository springDataRatingRepository;

    @Autowired
    public RatingBridge(SpringDataRatingRepository springDataRatingRepository) {
        this.springDataRatingRepository = springDataRatingRepository;
    }

    @Override
    public boolean existsById(RatingKey ratingKey) {
        return this.springDataRatingRepository.existsById(ratingKey);
    }

    @Override
    public Rating save(Rating rating) {
        return this.springDataRatingRepository.saveAndFlush(rating);
    }

    @Override
    public Rating getById(RatingKey ratingKey) {
        return this.springDataRatingRepository.getById(ratingKey);
    }

    @Override
    public List<Rating> getByUser(User user) {
        return this.springDataRatingRepository.getByUser(user);
    }

    @Override
    public List<Rating> getAllRatingsOfEpisode(Long episodeId) {
        return this.springDataRatingRepository.getAllRatingsOfEpisode(episodeId);
    }

    @Override
    public List<Rating> findAll() {
        return this.springDataRatingRepository.findAll();
    }
}