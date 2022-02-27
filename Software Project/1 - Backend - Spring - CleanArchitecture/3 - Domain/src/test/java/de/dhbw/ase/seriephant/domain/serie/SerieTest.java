package de.dhbw.ase.seriephant.domain.serie;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerieTest {

    private final Long SERIE_ID = 69L;
    private final Long NEW_SERIE_ID = 72L;
    private final String SERIE_TITLE = "serieTitle";
    private final String NEW_SERIE_TITLE = "newSerieTitle";
    private final String SERIE_DESCRIPTION = "serieDescription";
    private final String NEW_SERIE_DESCRIPTION = "newSerieDescription";
    private final Integer SERIE_RELEASE_YEAR = 1290;
    private final Integer NEW_SERIE_RELEASE_YEAR = 2290;
    private final Genre GENRE = new Genre("genreTitle", "genreDescription");
    private final Genre NEW_GENRE = new Genre("newGenreTitle", "newGenreDescription");

    private Serie serie;

    @BeforeEach
    public void init() {
        this.serie = new Serie(this.SERIE_TITLE, this.SERIE_DESCRIPTION, this.SERIE_RELEASE_YEAR, this.GENRE);
    }

    @Test
    void getId() {
        assertEquals(this.SERIE_ID, this.serie.getId());
    }

    @Test
    void getTitle() {
        assertEquals(this.SERIE_TITLE, this.serie.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(this.SERIE_DESCRIPTION, this.serie.getDescription());
    }

    @Test
    void getReleaseYear() {
        assertEquals(this.SERIE_RELEASE_YEAR, this.serie.getReleaseYear());
    }

    @Test
    void getGenre() {
        assertEquals(this.GENRE, this.serie.getGenre());
    }

    @Test
    void setId() {
        this.serie.setId(this.NEW_SERIE_ID);
        assertEquals(this.NEW_SERIE_ID, this.serie.getId());
    }

    @Test
    void setTitle() {
        this.serie.setTitle(this.NEW_SERIE_TITLE);
        assertEquals(this.NEW_SERIE_TITLE, this.serie.getTitle());
    }

    @Test
    void setDescription() {
        this.serie.setDescription(this.NEW_SERIE_DESCRIPTION);
        assertEquals(this.NEW_SERIE_DESCRIPTION, this.serie.getDescription());
    }

    @Test
    void setReleaseYear() {
        this.serie.setReleaseYear(this.NEW_SERIE_RELEASE_YEAR);
        assertEquals(this.NEW_SERIE_RELEASE_YEAR, this.serie.getReleaseYear());
    }

    @Test
    void setGenre() {
        this.serie.setGenre(this.NEW_GENRE);
        assertEquals(this.NEW_GENRE, this.serie.getGenre());
    }
}
