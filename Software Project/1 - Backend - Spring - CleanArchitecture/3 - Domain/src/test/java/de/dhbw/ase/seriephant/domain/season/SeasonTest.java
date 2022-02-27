package de.dhbw.ase.seriephant.domain.season;

import de.dhbw.ase.seriephant.domain.serie.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeasonTest {
    private final Long SEASON_ID = 3L;
    private final Long NEW_SEASON_ID = 2L;
    private final Integer SEASON_NUMBER = 3;
    private final Integer NEW_SEASON_NUMBER = 3;
    private final Serie SERIE = new Serie("serieTitle", "serieDescription", 1290);
    private final Serie NEW_SERIE = new Serie("newSerieTitle", "newSerieDescription", 2290);

    private Season testSeason;

    @BeforeEach
    void init() {
        this.testSeason = new Season(this.SEASON_ID, this.SEASON_NUMBER, this.SERIE);
    }

    @Test
    void getId() {
        assertEquals(this.SEASON_ID, this.testSeason.getId());
    }

    @Test
    void getSeasonNumber() {
        assertEquals(this.SEASON_NUMBER, this.testSeason.getSeasonNumber());
    }

    @Test
    void getSerie() {
        assertEquals(this.SERIE, this.testSeason.getSerie());
    }

    @Test
    void setId() {
        this.testSeason.setId(this.NEW_SEASON_ID);
        assertEquals(this.NEW_SEASON_ID, this.testSeason.getId());
    }

    @Test
    void setSeasonNumber() {
        this.testSeason.setSeasonNumber(this.NEW_SEASON_NUMBER);
        assertEquals(this.NEW_SEASON_NUMBER, this.testSeason.getSeasonNumber());
    }

    @Test
    void setSerie() {
        this.testSeason.setSerie(this.NEW_SERIE);
        assertEquals(this.NEW_SERIE, this.testSeason.getSerie());
    }
}