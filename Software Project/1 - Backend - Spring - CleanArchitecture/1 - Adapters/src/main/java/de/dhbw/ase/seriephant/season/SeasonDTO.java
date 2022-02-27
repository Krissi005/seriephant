package de.dhbw.ase.seriephant.season;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.serie.SerieDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "seasonNumber",
        "serie"
})
@Generated("jsonschema2pojo")
public class SeasonDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("seasonNumber")
    private Integer seasonNumber;
    @JsonProperty("serie")
    @JsonIgnoreProperties("genre")
    private SerieDTO serieDTO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SeasonDTO(Long id, Integer seasonNumber, SerieDTO serieDTO) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.serieDTO = serieDTO;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("seasonNumber")
    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    @JsonProperty("seasonNumber")
    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    @JsonProperty("serie")
    public SerieDTO getSerieDTO() {
        return serieDTO;
    }

    @JsonProperty("serie")
    public void setSerieDTO(SerieDTO serieDTO) {
        this.serieDTO = serieDTO;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}