package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.rating.RatingKey;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingKeyDTOToRatingKeyMapper implements Function<RatingKeyDTO, RatingKey> {
    @Override
    public RatingKey apply(RatingKeyDTO ratingKeyDTO) {
        return this.map(ratingKeyDTO);
    }

    private RatingKey map(RatingKeyDTO ratingKeyDTO) {
        return new RatingKey(
                ratingKeyDTO.getUserId(),
                ratingKeyDTO.getEpisodeId()
        );
    }
}
