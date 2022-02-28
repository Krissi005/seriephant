package de.dhbw.ase.seriephant.serie;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import de.dhbw.ase.seriephant.domain.genre.GenreRepository;
import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.domain.season.SeasonRepository;
import de.dhbw.ase.seriephant.domain.serie.Serie;
import de.dhbw.ase.seriephant.domain.serie.SerieRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SerieApplicationServiceTest {

    @Mock
    SerieRepository serieRepository;

    @Mock
    GenreRepository genreRepository;

    @Mock
    SeasonRepository seasonRepository;

    @InjectMocks
    SerieApplicationService serieApplicationService;

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
    private Season season1;
    private Season season2;

    @BeforeEach
    public void init() {
        this.serie = new Serie(SERIE_ID, SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE);
        this.season1 = new Season(1L, 1, this.serie);
        this.season2 = new Season(2L, 2, this.serie);
        this.serie.setSeasons(Arrays.asList(this.season1, this.season2));
    }

    @Test
    void saveSerie() throws ValidationException {
        Serie serie = new Serie(SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE);

        doReturn(serie).when(this.serieRepository).save(serie);
        Serie createdSerie = this.serieApplicationService.saveSerie(serie);

        verify(this.serieRepository, times(1)).save(any(Serie.class));

        assertEquals(serie, createdSerie);
    }

    @Test
    void saveSerieWithParams() {
        Serie serie = new Serie(SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE);

        doReturn(serie).when(this.serieRepository).save(any(Serie.class));
        Serie createdSerie = this.serieApplicationService.saveSerie(SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR);

        verify(this.serieRepository, times(1)).save(any(Serie.class));

        assertEquals(serie, createdSerie);
    }

    @Test
    void saveSerieWithGenre() throws ValidationException {
        Serie serie = new Serie(SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE);

        doReturn(true).when(this.genreRepository).existsById(GENRE.getId());
        doReturn(GENRE).when(this.genreRepository).getById(GENRE.getId());
        doReturn(serie).when(this.serieRepository).save(any(Serie.class));
        Serie createdSerie = this.serieApplicationService.saveSerie(SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE.getId());

        verify(this.serieRepository, times(1)).save(any(Serie.class));

        assertEquals(serie, createdSerie);
    }

    @Test
    void getSerieByIdSuccesfull() throws ValidationException {
        Serie expectedSerie = new Serie(SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE);

        doReturn(true).when(this.serieRepository).existsById(SERIE_ID);
        doReturn(expectedSerie).when(this.serieRepository).getById(SERIE_ID);

        Serie actualSerie = this.serieApplicationService.getSerieById(SERIE_ID);

        verify(this.serieRepository, times(1)).existsById(SERIE_ID);
        verify(this.serieRepository, times(1)).getById(SERIE_ID);

        assertEquals(expectedSerie, actualSerie);
    }

    @Test
    void getSerieByIdFailed() {
        Exception ex = assertThrows(ValidationException.class, () -> this.serieApplicationService.getSerieById(SERIE_ID));

        verify(this.serieRepository, times(1)).existsById(SERIE_ID);

        assertEquals("Id of Serie is not known.", ex.getMessage());
    }

    @Test
    void getAllSeries() {
        Serie serie1 = new Serie("Title1", "Description1", 1234);
        Serie serie2 = new Serie("Title2", "Description2");
        Serie serie3 = new Serie("Title3", "Description3", GENRE);

        List<Serie> expectedSeries = Arrays.asList(serie1, serie2, serie3);

        doReturn(Arrays.asList(serie1, serie2, serie3)).when(this.serieRepository).findAll();

        List<Serie> actualSeries = this.serieApplicationService.getAllSeries();

        verify(this.serieRepository, times(1)).findAll();

        assertEquals(expectedSeries, actualSeries);
    }

    @Test
    void getSeriesByTitleAndAndReleaseYear() {
        Serie serie1 = new Serie("Title1", "Description1", 1234);
        Serie serie2 = new Serie("Title1", "Description2", 1234);

        List<Serie> expectedSeries = Arrays.asList(serie1, serie2);

        doReturn(Arrays.asList(serie1, serie2)).when(this.serieRepository).getSeriesByTitleAndAndReleaseYear("Title1", 1234);

        List<Serie> actualSeries = this.serieApplicationService.getSeriesByTitleAndAndReleaseYear("Title1", 1234);

        verify(this.serieRepository, times(1)).getSeriesByTitleAndAndReleaseYear("Title1", 1234);

        assertEquals(expectedSeries, actualSeries);
    }

    @Test
    void updateSerieSuccessfull() throws ValidationException {
        Serie newSerie = new Serie(SERIE_ID, NEW_SERIE_TITLE, NEW_SERIE_DESCRIPTION, NEW_SERIE_RELEASE_YEAR, GENRE);

        doReturn(true).when(this.serieRepository).existsById(SERIE_ID);
        doReturn(this.serie).when(this.serieRepository).getById(SERIE_ID);
        doReturn(newSerie).when(this.serieRepository).save(any(Serie.class));

        Serie updatedSerie = this.serieApplicationService.updateSerie(newSerie);

        verify(this.serieRepository, times(1)).existsById(SERIE_ID);
        verify(this.serieRepository, times(1)).getById(SERIE_ID);
        verify(this.serieRepository, times(1)).save(any(Serie.class));

        assertEquals(newSerie.getTitle(), updatedSerie.getTitle());
        assertEquals(newSerie.getDescription(), updatedSerie.getDescription());
        assertEquals(newSerie.getReleaseYear(), updatedSerie.getReleaseYear());
        assertEquals(newSerie.getDescription(), updatedSerie.getDescription());
        assertEquals(newSerie.getGenre(), updatedSerie.getGenre());
    }

    @Test
    void updateSerieFailed() {
        Serie serie = new Serie(SERIE_ID, SERIE_TITLE, SERIE_DESCRIPTION, SERIE_RELEASE_YEAR, GENRE);

        Exception ex = assertThrows(ValidationException.class, () -> this.serieApplicationService.updateSerie(serie));

        verify(this.serieRepository, times(1)).existsById(SERIE_ID);
        verify(this.serieRepository, times(0)).save(any(Serie.class));

        assertEquals("Id of Serie is not known.", ex.getMessage());
    }

    @Test
    void deleteSerie() throws ValidationException {
        doReturn(true).when(this.serieRepository).existsById(SERIE_ID);
        this.serieApplicationService.deleteSerie(SERIE_ID);

        verify(this.serieRepository, times(1)).deleteById(SERIE_ID);

    }

    @Test
    void deleteSerieWithAllSeasons() throws ValidationException {
        doReturn(true).when(this.serieRepository).existsById(SERIE_ID);
        doReturn(this.serie).when(this.serieRepository).getById(SERIE_ID);

        this.serieApplicationService.deleteSerieWithAllSeasons(SERIE_ID);

        verify(this.serieRepository, times(1)).deleteById(SERIE_ID);
        verify(this.seasonRepository, times(2)).deleteById(any(Long.class));
    }
}