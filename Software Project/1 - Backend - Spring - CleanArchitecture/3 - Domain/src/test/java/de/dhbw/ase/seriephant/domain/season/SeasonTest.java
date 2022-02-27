package de.dhbw.ase.seriephant.domain.season;

import de.dhbw.ase.seriephant.domain.serie.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeasonTest {
    private static final Long SEASON_ID = 3L;
    private static final Long NEW_SEASON_ID = 2L;
    private static final Integer SEASON_NUMBER = 3;
    private static final Integer NEW_SEASON_NUMBER = 3;
    private static final Serie SERIE = new Serie("serieTitle", "serieDescription", 1290);
    private static final Serie NEW_SERIE = new Serie("newSerieTitle", "newSerieDescription", 2290);

    private Season testSeason;

    @BeforeEach
    void init() {
        this.testSeason = new Season(SEASON_ID, SEASON_NUMBER, SERIE);
    }

    @Test
    void getId() {
        assertEquals(SEASON_ID, this.testSeason.getId());
    }

    @Test
    void getSeasonNumber() {
        assertEquals(SEASON_NUMBER, this.testSeason.getSeasonNumber());
    }

    @Test
    void getSerie() {
        assertEquals(SERIE, this.testSeason.getSerie());
    }

    @Test
    void setId() {
        this.testSeason.setId(NEW_SEASON_ID);
        assertEquals(NEW_SEASON_ID, this.testSeason.getId());
    }

    @Test
    void setSeasonNumber() {
        this.testSeason.setSeasonNumber(NEW_SEASON_NUMBER);
        assertEquals(NEW_SEASON_NUMBER, this.testSeason.getSeasonNumber());
    }

    @Test
    void setSerie() {
        this.testSeason.setSerie(NEW_SERIE);
        assertEquals(NEW_SERIE, this.testSeason.getSerie());
    }
}