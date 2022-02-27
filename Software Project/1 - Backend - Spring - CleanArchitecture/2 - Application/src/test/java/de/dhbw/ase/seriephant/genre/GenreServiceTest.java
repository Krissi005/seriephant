package de.dhbw.ase.seriephant.genre;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import de.dhbw.ase.seriephant.domain.genre.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    GenreApplicationService genreApplicationService;

    private final String genreTitle = "genreTitle";
    private final String newGenreTitle = "newGenreTitle";
    private final String genreDescription = "genreDescription";
    private final String newGenreDescription = "newGenreDescription";

    private final Long genreId = 52L;

    @Test
    void saveGenreFromDTO() {
        Genre genre = new Genre(this.genreTitle, this.genreDescription);

        doReturn(genre).when(this.genreRepository).save(genre);
        Genre createdGenre = this.genreApplicationService.saveGenre(genre);

        verify(this.genreRepository, times(1)).save(any(Genre.class));

        assertThat(createdGenre).isEqualTo(genre);
    }

    @Test
    void saveGenre() {
        Genre genre = new Genre(this.genreTitle, this.genreDescription);

        doReturn(genre).when(this.genreRepository).save(any(Genre.class));
        Genre createdGenre = this.genreApplicationService.saveGenre(this.genreTitle, this.genreDescription);

        verify(this.genreRepository, times(1)).save(any(Genre.class));

        assertEquals(genre, createdGenre);
    }

    @Test
    void getGenreByIdSuccessful() throws ValidationException {
        Genre expectedGenre = new Genre(this.genreId, "Title1", "Description1");

        doReturn(true).when(this.genreRepository).existsById(this.genreId);
        doReturn(expectedGenre).when(this.genreRepository).getById(this.genreId);

        Genre actualGenre = this.genreApplicationService.getGenreById(this.genreId);

        verify(this.genreRepository, times(1)).existsById(this.genreId);
        verify(this.genreRepository, times(1)).getById(this.genreId);

        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    void getGenreByIdFailed() throws ValidationException {

        Exception ex = assertThrows(ValidationException.class, () -> this.genreApplicationService.getGenreById(this.genreId));

        verify(this.genreRepository, times(1)).existsById(this.genreId);

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
        Genre genre = new Genre(this.genreId, this.genreTitle, this.genreDescription);
        Genre newGenre = new Genre(this.genreId, this.newGenreTitle, this.newGenreDescription);

        doReturn(true).when(this.genreRepository).existsById(this.genreId);
        doReturn(genre).when(this.genreRepository).getById(this.genreId);
        doReturn(genre).when(this.genreRepository).save(genre);

        Genre updatedGenre = this.genreApplicationService.updateGenre(newGenre);

        verify(this.genreRepository, times(1)).existsById(this.genreId);
        verify(this.genreRepository, times(1)).save(any(Genre.class));

        assertEquals(updatedGenre.getTitle(), newGenre.getTitle());
        assertEquals(updatedGenre.getDescription(), newGenre.getDescription());
    }

    @Test
    void updateGenreFailed() {
        Genre genre = new Genre(this.genreId, this.newGenreTitle, this.newGenreDescription);

        Exception ex = assertThrows(ValidationException.class, () -> this.genreApplicationService.updateGenre(genre));

        verify(this.genreRepository, times(1)).existsById(this.genreId);
        verify(this.genreRepository, times(0)).save(any(Genre.class));

        assertEquals("Id of Genre is not known.", ex.getMessage());
    }

    @Test
    void deleteGenre() {
        this.genreApplicationService.deleteGenre(this.genreId);

        verify(this.genreRepository, times(1)).deleteById(this.genreId);
    }
}
