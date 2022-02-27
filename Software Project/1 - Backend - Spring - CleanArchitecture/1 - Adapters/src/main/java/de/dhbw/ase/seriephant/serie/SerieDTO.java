package de.dhbw.ase.seriephant.serie;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.genre.GenreDTO;
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
        "title",
        "description",
        "releaseYear",
        "genre"
})
@Generated("jsonschema2pojo")
public class SerieDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("releaseYear")
    private Integer releaseYear;
    @JsonProperty("genre")
    private GenreDTO genreDTO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SerieDTO(Long id, String title, String description, Integer releaseYear, GenreDTO genreDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.genreDTO = genreDTO;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("releaseYear")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @JsonProperty("releaseYear")
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @JsonProperty("genre")
    public GenreDTO getGenreDTO() {
        return genreDTO;
    }

    @JsonProperty("genre")
    public void setGenreDTO(GenreDTO genreDTO) {
        this.genreDTO = genreDTO;
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