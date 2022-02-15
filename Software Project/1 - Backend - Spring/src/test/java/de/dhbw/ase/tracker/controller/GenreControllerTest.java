package de.dhbw.ase.tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.ase.tracker.model.Genre;
import de.dhbw.ase.tracker.model.GenreDTO;
import de.dhbw.ase.tracker.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreControllerTest.class)
public class GenreControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GenreController genreController;

    @MockBean
    GenreService genreService;

    Long genreId = 53L;
    String genreTitle = "genreTitle";
    String genreDescription = "genreDescription";
    String newGenreTitle = "newGenreTitle";
    String newGenreDescription = "newGenreDescription";

    Genre genreTest = new Genre(genreId, genreTitle, genreDescription, new ArrayList<>());

    Genre genre1 = new Genre("Title1", "Description1");
    Genre genre2 = new Genre("Title2", "Description2");
    Genre genre3 = new Genre("Title3", "Description3");

    @Test
    void createGenreFromDTO() throws Exception {
        GenreDTO genreDTO = new GenreDTO(genreTitle, genreDescription);
        doReturn(genreTest).when(genreController).createGenre(genreDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/genre/create")
                        .content(objectMapper.writeValueAsString(genreDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(genreTest.getTitle(), readOutGenre.getTitle());
        assertEquals(genreTest.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void createGenre() throws Exception {
        doReturn(genreTest).when(genreController).createGenre(genreTitle, genreDescription);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/genre/new")
                        .param("title", genreTitle)
                        .param("description", genreDescription)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(genreTest.getTitle(), readOutGenre.getTitle());
        assertEquals(genreTest.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void getAllGenres() throws Exception {
        List<Genre> genres = Arrays.asList(genre1, genre2, genre3);
        doReturn(genres).when(genreController).getAllGenres();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/genre/read")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        List<Genre> readOutGenres = Arrays.asList(objectMapper.reader().forType(Genre[].class).readValue(responseBody));
        for(int i = 0; i<3;i++){
            assertEquals(genres.get(i).getTitle(),readOutGenres.get(i).getTitle());
            assertEquals(genres.get(i).getDescription(),readOutGenres.get(i).getDescription());
        }
    }

    @Test
    void updateGenre() throws Exception {
        GenreDTO genreDTO = new GenreDTO(newGenreTitle, newGenreDescription);
        Genre changedGenre = new Genre(newGenreTitle, newGenreDescription);
        doReturn(changedGenre).when(genreController).updateGenre(genreId, genreDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/genre/update")
                        .param("genreId", genreId.toString())
                        .content(objectMapper.writeValueAsString(genreDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(changedGenre.getTitle(), readOutGenre.getTitle());
        assertEquals(changedGenre.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void deleteGenre() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/genre/delete")
                        .param("genreId", genreId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
