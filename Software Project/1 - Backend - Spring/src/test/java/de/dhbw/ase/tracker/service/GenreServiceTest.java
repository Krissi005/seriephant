package de.dhbw.ase.tracker.service;

import de.dhbw.ase.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.model.Genre;
import de.dhbw.ase.tracker.model.GenreDTO;
import de.dhbw.ase.tracker.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    GenreService genreService;

    private String genreTitle = "genreTitle";
    private String newGenreTitle = "newGenreTitle";
    private String genreDescription = "genreDescription";
    private String newGenreDescritpion = "newGenreDescription";

    private Long genreId = 52L;

    @Test
    void saveGenreFromDTO() {
        GenreDTO genreDTO = new GenreDTO(genreTitle, genreDescription);
        Genre genre = DTOMapper.convertDTOToGenre(genreDTO);

        doReturn(genre).when(genreRepository).save(genre);
        Genre createdGenre = genreRepository.save(genre);

        verify(genreRepository, times(1)).save(any(Genre.class));

        assertThat(createdGenre).isEqualTo(genre);
    }

    @Test
    void saveGenre() {
        Genre genre = new Genre(genreTitle, genreDescription);

        doReturn(genre).when(genreRepository).save(genre);
        Genre createdGenre = genreRepository.save(genre);

        verify(genreRepository, times(1)).save(any(Genre.class));

        assertEquals(createdGenre,genre);
    }

    @Test
    void getAllGenres() {
        Genre genre1 = new Genre("Title1", "Description1");
        Genre genre2 = new Genre("Title2", "Description2");
        Genre genre3 = new Genre("Title3", "Description3");

        List<Genre> expectedGenres = Arrays.asList(genre1, genre2, genre3);

        doReturn(Arrays.asList(genre1, genre2, genre3)).when(genreRepository).findAll();

        List<Genre> actualGenres = genreService.getAllGenres();

        verify(genreRepository, times(1)).findAll();

        assertEquals(expectedGenres,actualGenres);
    }

    @Test
    void updateGenreSuccessful() throws ValidationException {
        Genre genre = new Genre(genreTitle, genreDescription);
        GenreDTO genreDTO = new GenreDTO(newGenreTitle, newGenreDescritpion);
        Genre newGenre = new Genre(newGenreTitle, newGenreDescritpion);

        doReturn(true).when(genreRepository).existsById(genreId);
        doReturn(genre).when(genreRepository).getById(genreId);
        doReturn(genre).when(genreRepository).save(genre);

        Genre updatedGenre = genreService.updateGenre(genreId, genreDTO);

        verify(genreRepository, times(1)).existsById(genreId);
        verify(genreRepository, times(1)).save(any(Genre.class));

        assertEquals(updatedGenre.getTitle(), newGenre.getTitle());
        assertEquals(updatedGenre.getDescription(), newGenre.getDescription());
    }

    @Test
    void updateGenreFailed() {
        GenreDTO genreDTO = new GenreDTO(newGenreTitle, newGenreDescritpion);

        Exception ex = assertThrows(ValidationException.class, ()-> genreService.updateGenre(genreId, genreDTO));

        verify(genreRepository, times(1)).existsById(genreId);
        verify(genreRepository, times(0)).save(any(Genre.class));

        assertEquals("Id is not known.", ex.getMessage());
    }

    @Test
    void deleteGenre(){
        genreService.deleteGenre(genreId);

        verify(genreRepository, times(1)).deleteById(genreId);
    }
}
