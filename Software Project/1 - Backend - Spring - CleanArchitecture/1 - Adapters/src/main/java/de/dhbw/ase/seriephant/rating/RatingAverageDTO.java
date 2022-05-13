package de.dhbw.ase.seriephant.rating;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.episode.EpisodeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "episodeId",
        "episodeTitle",
        "ratingAverage",
        "numberOfRatings"
})
@Generated("jsonschema2pojo")
public final class RatingAverageDTO {

    @JsonIgnoreProperties({"actors"})
    @JsonProperty("episode")
    private EpisodeDTO episodeDTO;

    @JsonProperty("ratingAverage")
    private Double ratingAverage;

    @JsonProperty("numberOfRatings")
    private Integer numberOfRatings;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public RatingAverageDTO(EpisodeDTO episodeDTO, Double ratingAverage, Integer numberOfRatings) {
        this.episodeDTO = episodeDTO;
        this.ratingAverage = ratingAverage;
        this.numberOfRatings = numberOfRatings;
    }

    @JsonProperty("episode")
    public EpisodeDTO getEpisodeDTO() {
        return episodeDTO;
    }

    @JsonProperty("ratingAverage")
    public Double getRatingAverage() {
        return ratingAverage;
    }

    @JsonProperty("numberOfRatings")
    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}