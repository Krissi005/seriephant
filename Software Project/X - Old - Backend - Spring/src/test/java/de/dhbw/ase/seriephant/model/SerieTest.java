package de.dhbw.ase.seriephant.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

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
    public void init(){
        genre = new Genre("genreTitle", "genreDescription");
        newGenre = new Genre("newGenreTitle", "newGenreDescription");
        serie = new Serie(serieTitle, serieDescription, serieReleaseYear, genre);
    }

    @Test
    void getId() {
        Serie serieMock = Mockito.mock(Serie.class);
        doReturn(serieId).when(serieMock).getId();
        assertEquals(serieMock.getId(), serieId);
    }
    
    @Test
    void getTitle(){
        assertEquals(serieTitle, serie.getTitle());
    }

    @Test
    void getDescription(){
        assertEquals(serieDescription, serie.getDescription());
    }

    @Test
    void getReleaseYear(){
        assertEquals(serieReleaseYear, serie.getReleaseYear());
    }

    @Test
    void getGenre(){
        assertEquals(genre, serie.getGenre());
    }

    @Test
    void setTitle(){
        serie.setTitle(newSerieTitle);
        assertEquals(newSerieTitle, serie.getTitle());
    }

    @Test
    void setDescription(){
        serie.setDescription(newSerieDescritpion);
        assertEquals(newSerieDescritpion, serie.getDescription());
    }

    @Test
    void setReleaseYear(){
        serie.setReleaseYear(newSerieReleaseYear);
        assertEquals(newSerieReleaseYear, serie.getReleaseYear());
    }

    @Test
    void setGenre(){
        serie.setGenre(newGenre);
        assertEquals(newGenre, serie.getGenre());
    }
}
