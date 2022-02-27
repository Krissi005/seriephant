package de.dhbw.ase.seriephant.domain.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreTest {

    private final Long GENRE_ID = 52L;
    private final Long NEW_GENRE_ID = 69L;
    private final String GENRE_TITLE = "genreTitle";
    private final String NEW_GENRE_TITLE = "newGenreTitle";
    private final String GENRE_DESCRIPTION = "genreDescription";
    private final String NEW_GENRE_DESCRIPTION = "newGenreDescription";

    private Genre testGenre;

    @BeforeEach
    public void init() {
        this.testGenre = new Genre(this.GENRE_ID, this.GENRE_TITLE, this.GENRE_DESCRIPTION);
    }

    @Test
    void getId() {
        assertEquals(this.GENRE_ID, this.testGenre.getId());
    }

    @Test
    void getTitle() {
        assertEquals(this.GENRE_TITLE, this.testGenre.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(this.GENRE_DESCRIPTION, this.testGenre.getDescription());
    }

    @Test
    void setId() {
        this.testGenre.setId(this.NEW_GENRE_ID);
        assertEquals(this.NEW_GENRE_ID, this.testGenre.getId());
    }

    @Test
    void setTitle() {
        this.testGenre.setTitle(this.NEW_GENRE_TITLE);
        assertEquals(this.NEW_GENRE_TITLE, this.testGenre.getTitle());
    }

    @Test
    void setDescription() {
        this.testGenre.setDescription(this.NEW_GENRE_DESCRIPTION);
        assertEquals(this.NEW_GENRE_DESCRIPTION, this.testGenre.getDescription());
    }
}
