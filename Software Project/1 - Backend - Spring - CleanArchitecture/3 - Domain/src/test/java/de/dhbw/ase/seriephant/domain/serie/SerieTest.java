package de.dhbw.ase.seriephant.domain.serie;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerieTest {

    private static final Long SERIE_ID = 69L;
    private static final Long NEW_SERIE_ID = 72L;
    private static final String SERIE_TITLE = "serieTitle";
    private static final String NEW_SERIE_TITLE = "newSerieTitle";
    private static final String SERIE_DESCRIPTION = "serieDescription";
    private static final String NEW_SERIE_DESCRIPTION = "newSerieDescription";
    private static final Integer SERIE_RELEASE_YEAR = 1290;
    private static final Integer NEW_SERIE_RELEASE_YEAR = 2290;
    private static final Genre GENRE = new Genre("genreTitle", "genreDescription");
    private static final Genre NEW_GENRE = new Genre("newGenreTitle", "newGenreDescription");

    private Serie serie;

    @BeforeEach
    public void init() {
        this.serie = new Serie(SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE);
    }

    @Test
    void getId() {
        assertEquals(SERIE_ID, this.serie.getId());
    }

    @Test
    void getTitle() {
        assertEquals(SERIE_TITLE, this.serie.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(SERIE_DESCRIPTION, this.serie.getDescription());
    }

    @Test
    void getReleaseYear() {
        assertEquals(SERIE_RELEASE_YEAR, this.serie.getReleaseYear());
    }

    @Test
    void getGenre() {
        assertEquals(GENRE, this.serie.getGenre());
    }

    @Test
    void setId() {
        this.serie.setId(NEW_SERIE_ID);
        assertEquals(NEW_SERIE_ID, this.serie.getId());
    }

    @Test
    void setTitle() {
        this.serie.setTitle(NEW_SERIE_TITLE);
        assertEquals(NEW_SERIE_TITLE, this.serie.getTitle());
    }

    @Test
    void setDescription() {
        this.serie.setDescription(NEW_SERIE_DESCRIPTION);
        assertEquals(NEW_SERIE_DESCRIPTION, this.serie.getDescription());
    }

    @Test
    void setReleaseYear() {
        this.serie.setReleaseYear(NEW_SERIE_RELEASE_YEAR);
        assertEquals(NEW_SERIE_RELEASE_YEAR, this.serie.getReleaseYear());
    }

    @Test
    void setGenre() {
        this.serie.setGenre(NEW_GENRE);
        assertEquals(NEW_GENRE, this.serie.getGenre());
    }
}
