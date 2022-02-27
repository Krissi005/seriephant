package de.dhbw.ase.seriephant.model.listener;

import de.dhbw.ase.seriephant.model.Genre;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.PrePersist;

public class GenreListener {
    private static Log log = LogFactory.getLog(GenreListener.class);

    @PrePersist
    private void beforeAnyUpdate(Genre genre) {
        log.info("[GENRE AUDIT] About to add a genre");
    }
}
