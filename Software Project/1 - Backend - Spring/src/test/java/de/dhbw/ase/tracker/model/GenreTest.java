package de.dhbw.ase.tracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class GenreTest {

    private String genreTitle = "genreTitle";
    private String newGenreTitle = "newGenreTitle";
    private String genreDescription = "genreDescription";
    private String newGenreDescritpion = "newGenreDescription";

    private Long genreId = 52L;

    private Genre testGenre;

    @BeforeEach
    public void init(){
        testGenre = new Genre(genreTitle, genreDescription);
    }

    @Test
    void getId() {
        Genre genreMock = Mockito.mock(Genre.class);
        doReturn(genreId).when(genreMock).getId();
        assertEquals(genreMock.getId(), genreId);
    }
    
    @Test
    void getTitle(){
        assertEquals(genreTitle, testGenre.getTitle());
    }

    @Test
    void getDescription(){
        assertEquals(genreDescription, testGenre.getDescription());
    }

    @Test
    void setTitle(){
        testGenre.setTitle(newGenreTitle);
        assertEquals(newGenreTitle, testGenre.getTitle());
    }

    @Test
    void setDescription(){
        testGenre.setDescription(newGenreDescritpion);
        assertEquals(newGenreDescritpion, testGenre.getDescription());
    }
}
