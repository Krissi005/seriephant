package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.rating.RatingKey;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingKeyToRatingKeyDTOMapper implements Function<RatingKey, RatingKeyDTO> {
    @Override
    public RatingKeyDTO apply(RatingKey ratingKey) {
        return this.map(ratingKey);
    }

    private RatingKeyDTO map(RatingKey ratingKey) {
        return new RatingKeyDTO(
                ratingKey.getUserId(),
                ratingKey.getEpisodeId()
        );
    }
}
