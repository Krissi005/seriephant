package de.dhbw.ase.seriephant.rating;

import com.fasterxml.jackson.annotation.*;
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

    @JsonProperty("episodeId")
    private Long episodeId;

    @JsonProperty("episodeTitle")
    private String episodeTitle;

    @JsonProperty("ratingAverage")
    private Double ratingAverage;

    @JsonProperty("numberOfRatings")
    private Integer numberOfRatings;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public RatingAverageDTO(Long episodeId, String episodeTitle, Double ratingAverage, Integer numberOfRatings) {
        this.episodeId = episodeId;
        this.episodeTitle = episodeTitle;
        this.ratingAverage = ratingAverage;
        this.numberOfRatings = numberOfRatings;
    }

    @JsonProperty("episodeId")
    public Long getEpisodeId() {
        return episodeId;
    }

    @JsonProperty("episodeTitle")
    public String getEpisodeTitle() {
        return episodeTitle;
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