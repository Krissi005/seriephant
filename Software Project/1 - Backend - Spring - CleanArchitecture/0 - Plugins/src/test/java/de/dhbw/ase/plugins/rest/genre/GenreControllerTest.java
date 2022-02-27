package de.dhbw.ase.plugins.rest.genre;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.ase.plugins.rest.GenreController;
import de.dhbw.ase.seriephant.domain.genre.Genre;
import de.dhbw.ase.seriephant.genre.GenreApplicationService;
import de.dhbw.ase.seriephant.genre.GenreDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest(classes = de.dhbw.ase.seriephant.SeriephantApplication.class)
@AutoConfigureMockMvc
public class GenreControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GenreController genreController;

    @MockBean
    GenreApplicationService genreApplicationService;

    Long genreId = 53L;
    String genreTitle = "genreTitle";
    String genreDescription = "genreDescription";
    String newGenreTitle = "newGenreTitle";
    String newGenreDescription = "newGenreDescription";

    Genre genreTest = new Genre(this.genreId, this.genreTitle, this.genreDescription, new ArrayList<>());
    GenreDTO genretestDTO = new GenreDTO(this.genreId, this.genreTitle, this.genreDescription);

    Genre genre1 = new Genre("Title1", "Description1");
    Genre genre2 = new Genre("Title2", "Description2");
    Genre genre3 = new Genre("Title3", "Description3");

    @Test
    void createGenreFromDTO() throws Exception {
        GenreDTO genreDTO = new GenreDTO(this.genreId, this.genreTitle, this.genreDescription);
        doReturn(this.genretestDTO).when(this.genreController).createGenre(genreDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/genre/create")
                        .content(this.objectMapper.writeValueAsString(genreDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = this.objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(this.genretestDTO.getTitle(), readOutGenre.getTitle());
        assertEquals(this.genretestDTO.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void createGenre() throws Exception {
        doReturn(this.genretestDTO).when(this.genreController).createGenre(this.genreTitle, this.genreDescription);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/genre/new")
                        .param("title", this.genreTitle)
                        .param("description", this.genreDescription)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = this.objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(this.genretestDTO.getTitle(), readOutGenre.getTitle());
        assertEquals(this.genretestDTO.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void getAllGenres() throws Exception {
        List<Genre> genres = Arrays.asList(this.genre1, this.genre2, this.genre3);
        doReturn(genres).when(this.genreController).getAllGenres();
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/genre/read")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        List<Genre> readOutGenres = Arrays.asList(this.objectMapper.reader().forType(Genre[].class).readValue(responseBody));
        for (int i = 0; i < 3; i++) {
            assertEquals(genres.get(i).getTitle(), readOutGenres.get(i).getTitle());
            assertEquals(genres.get(i).getDescription(), readOutGenres.get(i).getDescription());
        }
    }

    @Test
    void updateGenre() throws Exception {
        GenreDTO genreDTO = new GenreDTO(this.genreId, this.newGenreTitle, this.newGenreDescription);
        GenreDTO changedGenreDTO = new GenreDTO(this.genreId, this.newGenreTitle, this.newGenreDescription);
        doReturn(changedGenreDTO).when(this.genreController).updateGenre(genreDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/genre/update")
                        .param("genreId", this.genreId.toString())
                        .content(this.objectMapper.writeValueAsString(genreDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = this.objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(changedGenreDTO.getTitle(), readOutGenre.getTitle());
        assertEquals(changedGenreDTO.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void deleteGenre() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/genre/delete")
                        .param("genreId", String.valueOf(this.genreId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
