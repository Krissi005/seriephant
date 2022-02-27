package de.dhbw.ase.seriephant.domain.serie;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerieTest {

    private String serieTitle = "serieTitle";
    private String newSerieTitle = "newSerieTitle";
    private String serieDescription = "serieDescription";
    private String newSerieDescritpion = "newSerieDescription";
    private Integer serieReleaseYear = 1290;
    private Integer newSerieReleaseYear = 1290;
    private Genre genre;
    private Genre newGenre;

    private Long serieId = 69L;

    private Serie serie;

    @BeforeEach
    public void init() {
        this.genre = new Genre("genreTitle", "genreDescription");
        this.newGenre = new Genre("newGenreTitle", "newGenreDescription");
        this.serie = new Serie(this.serieTitle, this.serieDescription, this.serieReleaseYear, this.genre);
    }

    @Test
    void getId() {
        Serie serieMock = Mockito.mock(Serie.class);
        Mockito.doReturn(this.serieId).when(serieMock).getId();
        assertEquals(serieMock.getId(), this.serieId);
    }

    @Test
    void getTitle() {
        assertEquals(this.serieTitle, this.serie.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(this.serieDescription, this.serie.getDescription());
    }

    @Test
    void getReleaseYear() {
        assertEquals(this.serieReleaseYear, this.serie.getReleaseYear());
    }

    @Test
    void getGenre() {
        assertEquals(this.genre, this.serie.getGenre());
    }

    @Test
    void setTitle() {
        this.serie.setTitle(this.newSerieTitle);
        assertEquals(this.newSerieTitle, this.serie.getTitle());
    }

    @Test
    void setDescription() {
        this.serie.setDescription(this.newSerieDescritpion);
        assertEquals(this.newSerieDescritpion, this.serie.getDescription());
    }

    @Test
    void setReleaseYear() {
        this.serie.setReleaseYear(this.newSerieReleaseYear);
        assertEquals(this.newSerieReleaseYear, this.serie.getReleaseYear());
    }

    @Test
    void setGenre() {
        this.serie.setGenre(this.newGenre);
        assertEquals(this.newGenre, this.serie.getGenre());
    }
}
