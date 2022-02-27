package de.dhbw.ase.seriephant.genre;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import de.dhbw.ase.seriephant.domain.genre.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreApplicationServiceTest {

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    GenreApplicationService genreApplicationService;

    private static final Long GENRE_ID = 52L;
    private static final String GENRE_TITLE = "genreTitle";
    private static final String NEW_GENRE_TITLE = "newGenreTitle";
    private static final String GENRE_DESCRIPTION = "genreDescription";
    private static final String NEW_GENRE_DESCRIPTION = "newGenreDescription";

    private Genre testGenre;

    @BeforeEach
    public void init() {
        this.testGenre = new Genre(GENRE_TITLE, GENRE_DESCRIPTION);
    }

    @Test
    void saveGenreFromDTO() throws ValidationException {
        Genre genre = new Genre(GENRE_TITLE, GENRE_DESCRIPTION);

        doReturn(genre).when(this.genreRepository).save(genre);
        Genre createdGenre = this.genreApplicationService.saveGenre(genre);

        verify(this.genreRepository, times(1)).save(any(Genre.class));

        assertEquals(genre, createdGenre);
    }

    @Test
    void saveGenre() {
        Genre genre = new Genre(GENRE_TITLE, GENRE_DESCRIPTION);

        doReturn(genre).when(this.genreRepository).save(any(Genre.class));
        Genre createdGenre = this.genreApplicationService.saveGenre(GENRE_TITLE, GENRE_DESCRIPTION);

        verify(this.genreRepository, times(1)).save(any(Genre.class));

        assertEquals(genre, createdGenre);
    }

    @Test
    void getGenreByIdSuccessful() throws ValidationException {
        Genre expectedGenre = new Genre(GENRE_ID, NEW_GENRE_TITLE, NEW_GENRE_DESCRIPTION);

        doReturn(true).when(this.genreRepository).existsById(GENRE_ID);
        doReturn(expectedGenre).when(this.genreRepository).getById(GENRE_ID);

        Genre actualGenre = this.genreApplicationService.getGenreById(GENRE_ID);

        verify(this.genreRepository, times(1)).existsById(GENRE_ID);
        verify(this.genreRepository, times(1)).getById(GENRE_ID);

        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    void getGenreByIdFailed() throws ValidationException {

        Exception ex = assertThrows(ValidationException.class, () -> this.genreApplicationService.getGenreById(GENRE_ID));

        verify(this.genreRepository, times(1)).existsById(GENRE_ID);

        assertEquals("Id of Genre is not known.", ex.getMessage());
    }

    @Test
    void getAllGenres() {
        Genre genre1 = new Genre("Title1", "Description1");
        Genre genre2 = new Genre("Title2", "Description2");
        Genre genre3 = new Genre("Title3", "Description3");

        List<Genre> expectedGenres = Arrays.asList(genre1, genre2, genre3);

        doReturn(Arrays.asList(genre1, genre2, genre3)).when(this.genreRepository).findAll();

        List<Genre> actualGenres = this.genreApplicationService.getAllGenres();

        verify(this.genreRepository, times(1)).findAll();

        assertEquals(expectedGenres, actualGenres);
    }

    @Test
    void updateGenreSuccessful() throws ValidationException {
        Genre genre = new Genre(GENRE_ID, GENRE_TITLE, GENRE_DESCRIPTION);
        Genre newGenre = new Genre(GENRE_ID, NEW_GENRE_TITLE, NEW_GENRE_DESCRIPTION);

        doReturn(true).when(this.genreRepository).existsById(GENRE_ID);
        doReturn(genre).when(this.genreRepository).getById(GENRE_ID);
        doReturn(genre).when(this.genreRepository).save(genre);

        Genre updatedGenre = this.genreApplicationService.updateGenre(newGenre);

        verify(this.genreRepository, times(1)).existsById(GENRE_ID);
        verify(this.genreRepository, times(1)).save(any(Genre.class));

        assertEquals(updatedGenre.getTitle(), newGenre.getTitle());
        assertEquals(updatedGenre.getDescription(), newGenre.getDescription());
    }

    @Test
    void updateGenreFailed() {
        Genre genre = new Genre(GENRE_ID, NEW_GENRE_TITLE, NEW_GENRE_DESCRIPTION);

        Exception ex = assertThrows(ValidationException.class, () -> this.genreApplicationService.updateGenre(genre));

        verify(this.genreRepository, times(1)).existsById(GENRE_ID);
        verify(this.genreRepository, times(0)).save(any(Genre.class));

        assertEquals("Id of Genre is not known.", ex.getMessage());
    }

    @Test
    void deleteGenre() {
        this.genreApplicationService.deleteGenre(GENRE_ID);

        verify(this.genreRepository, times(1)).deleteById(GENRE_ID);
    }
}
