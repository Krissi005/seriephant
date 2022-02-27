package de.dhbw.ase.seriephant.domain.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreTest {

    private static final Long GENRE_ID = 52L;
    private static final Long NEW_GENRE_ID = 69L;
    private static final String GENRE_TITLE = "genreTitle";
    private static final String NEW_GENRE_TITLE = "newGenreTitle";
    private static final String GENRE_DESCRIPTION = "genreDescription";
    private static final String NEW_GENRE_DESCRIPTION = "newGenreDescription";

    private Genre testGenre;

    @BeforeEach
    public void init() {
        this.testGenre = new Genre(GENRE_ID, GENRE_TITLE, GENRE_DESCRIPTION);
    }

    @Test
    void getId() {
        assertEquals(GENRE_ID, this.testGenre.getId());
    }

    @Test
    void getTitle() {
        assertEquals(GENRE_TITLE, this.testGenre.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(GENRE_DESCRIPTION, this.testGenre.getDescription());
    }

    @Test
    void setId() {
        this.testGenre.setId(NEW_GENRE_ID);
        assertEquals(NEW_GENRE_ID, this.testGenre.getId());
    }

    @Test
    void setTitle() {
        this.testGenre.setTitle(NEW_GENRE_TITLE);
        assertEquals(NEW_GENRE_TITLE, this.testGenre.getTitle());
    }

    @Test
    void setDescription() {
        this.testGenre.setDescription(NEW_GENRE_DESCRIPTION);
        assertEquals(NEW_GENRE_DESCRIPTION, this.testGenre.getDescription());
    }
}
