package de.dhbw.ase.seriephant.domain.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreTest {

    private String genreTitle = "genreTitle";
    private String newGenreTitle = "newGenreTitle";
    private String genreDescription = "genreDescription";
    private String newGenreDescritpion = "newGenreDescription";

    private Long genreId = 52L;

    private Genre testGenre;

    @BeforeEach
    public void init() {
        this.testGenre = new Genre(this.genreTitle, this.genreDescription);
    }

    @Test
    void getId() {
        Genre genreMock = Mockito.mock(Genre.class);
        Mockito.doReturn(this.genreId).when(genreMock).getId();
        assertEquals(genreMock.getId(), this.genreId);
    }

    @Test
    void getTitle() {
        assertEquals(this.genreTitle, this.testGenre.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(this.genreDescription, this.testGenre.getDescription());
    }

    @Test
    void setTitle() {
        this.testGenre.setTitle(this.newGenreTitle);
        assertEquals(this.newGenreTitle, this.testGenre.getTitle());
    }

    @Test
    void setDescription() {
        this.testGenre.setDescription(this.newGenreDescritpion);
        assertEquals(this.newGenreDescritpion, this.testGenre.getDescription());
    }
}
