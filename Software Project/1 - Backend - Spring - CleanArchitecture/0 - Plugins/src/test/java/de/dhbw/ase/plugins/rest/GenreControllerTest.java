package de.dhbw.ase.plugins.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.ase.seriephant.domain.genre.Genre;
import de.dhbw.ase.seriephant.genre.GenreApplicationService;
import de.dhbw.ase.seriephant.genre.GenreDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = de.dhbw.ase.seriephant.SeriephantApplication.class)
@AutoConfigureMockMvc
class GenreControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GenreController genreController;

    @MockBean
    GenreApplicationService genreApplicationService;

    private static final Long GENRE_ID = 53L;
    private static final Long NEW_GENRE_ID = 54L;
    private static final String GENRE_TITLE = "genreTitle";
    private static final String GENRE_DESCRIPTION = "genreDescription";
    private static final String NEW_GENRE_TITLE = "newGenreTitle";
    private static final String NEW_GENRE_DESCRIPTION = "newGenreDescription";

    private static final Genre GENRE_1 = new Genre("Title1", "Description1");
    private static final Genre GENRE_2 = new Genre("Title2", "Description2");
    private static final Genre GENRE_3 = new Genre("Title3", "Description3");

    private GenreDTO genreDTO;

    @BeforeEach
    void init() {
        this.genreDTO = new GenreDTO(GENRE_ID, GENRE_TITLE, GENRE_DESCRIPTION);
    }

    @Test
    void createGenreFromDTO() throws Exception {
        doReturn(this.genreDTO).when(this.genreController).createGenre(this.genreDTO);
        MvcResult mvcResult = this.mockMvc.perform(post("/genre/create")
                        .content(this.objectMapper.writeValueAsString(this.genreDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = this.objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(this.genreDTO.getTitle(), readOutGenre.getTitle());
        assertEquals(this.genreDTO.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void createGenre() throws Exception {
        doReturn(this.genreDTO).when(this.genreController).createGenre(GENRE_TITLE, GENRE_DESCRIPTION);
        MvcResult mvcResult = this.mockMvc.perform(post("/genre/new")
                        .param("title", GENRE_TITLE)
                        .param("description", GENRE_DESCRIPTION)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        Genre readOutGenre = this.objectMapper.reader().forType(Genre.class).readValue(responseBody);
        assertEquals(this.genreDTO.getTitle(), readOutGenre.getTitle());
        assertEquals(this.genreDTO.getDescription(), readOutGenre.getDescription());
    }

    @Test
    void getAllGenres() throws Exception {
        List<Genre> genres = Arrays.asList(GENRE_1, GENRE_2, GENRE_3);
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
        GenreDTO changedGenreDTO = new GenreDTO(GENRE_ID, NEW_GENRE_TITLE, NEW_GENRE_DESCRIPTION);
        doReturn(changedGenreDTO).when(this.genreController).updateGenre(changedGenreDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/genre/update")
                        .content(this.objectMapper.writeValueAsString(changedGenreDTO))
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
                        .param("genreId", String.valueOf(GENRE_ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
