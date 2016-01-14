package com.natanverdes.ligabaloncesto.web.rest;

import com.natanverdes.ligabaloncesto.Application;
import com.natanverdes.ligabaloncesto.domain.JugadorJuegaPartido;
import com.natanverdes.ligabaloncesto.repository.JugadorJuegaPartidoRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the JugadorJuegaPartidoResource REST controller.
 *
 * @see JugadorJuegaPartidoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class JugadorJuegaPartidoResourceIntTest {


    private static final Integer DEFAULT_CANASTAS = 1;
    private static final Integer UPDATED_CANASTAS = 2;

    private static final Integer DEFAULT_ASISTENCIAS = 1;
    private static final Integer UPDATED_ASISTENCIAS = 2;

    private static final Integer DEFAULT_REBOTES = 1;
    private static final Integer UPDATED_REBOTES = 2;

    private static final Integer DEFAULT_FALTAS = 1;
    private static final Integer UPDATED_FALTAS = 2;

    @Inject
    private JugadorJuegaPartidoRepository jugadorJuegaPartidoRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restJugadorJuegaPartidoMockMvc;

    private JugadorJuegaPartido jugadorJuegaPartido;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JugadorJuegaPartidoResource jugadorJuegaPartidoResource = new JugadorJuegaPartidoResource();
        ReflectionTestUtils.setField(jugadorJuegaPartidoResource, "jugadorJuegaPartidoRepository", jugadorJuegaPartidoRepository);
        this.restJugadorJuegaPartidoMockMvc = MockMvcBuilders.standaloneSetup(jugadorJuegaPartidoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        jugadorJuegaPartido = new JugadorJuegaPartido();
        jugadorJuegaPartido.setCanastas(DEFAULT_CANASTAS);
        jugadorJuegaPartido.setAsistencias(DEFAULT_ASISTENCIAS);
        jugadorJuegaPartido.setRebotes(DEFAULT_REBOTES);
        jugadorJuegaPartido.setFaltas(DEFAULT_FALTAS);
    }

    @Test
    @Transactional
    public void createJugadorJuegaPartido() throws Exception {
        int databaseSizeBeforeCreate = jugadorJuegaPartidoRepository.findAll().size();

        // Create the JugadorJuegaPartido

        restJugadorJuegaPartidoMockMvc.perform(post("/api/jugadorJuegaPartidos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jugadorJuegaPartido)))
                .andExpect(status().isCreated());

        // Validate the JugadorJuegaPartido in the database
        List<JugadorJuegaPartido> jugadorJuegaPartidos = jugadorJuegaPartidoRepository.findAll();
        assertThat(jugadorJuegaPartidos).hasSize(databaseSizeBeforeCreate + 1);
        JugadorJuegaPartido testJugadorJuegaPartido = jugadorJuegaPartidos.get(jugadorJuegaPartidos.size() - 1);
        assertThat(testJugadorJuegaPartido.getCanastas()).isEqualTo(DEFAULT_CANASTAS);
        assertThat(testJugadorJuegaPartido.getAsistencias()).isEqualTo(DEFAULT_ASISTENCIAS);
        assertThat(testJugadorJuegaPartido.getRebotes()).isEqualTo(DEFAULT_REBOTES);
        assertThat(testJugadorJuegaPartido.getFaltas()).isEqualTo(DEFAULT_FALTAS);
    }

    @Test
    @Transactional
    public void getAllJugadorJuegaPartidos() throws Exception {
        // Initialize the database
        jugadorJuegaPartidoRepository.saveAndFlush(jugadorJuegaPartido);

        // Get all the jugadorJuegaPartidos
        restJugadorJuegaPartidoMockMvc.perform(get("/api/jugadorJuegaPartidos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(jugadorJuegaPartido.getId().intValue())))
                .andExpect(jsonPath("$.[*].canastas").value(hasItem(DEFAULT_CANASTAS)))
                .andExpect(jsonPath("$.[*].asistencias").value(hasItem(DEFAULT_ASISTENCIAS)))
                .andExpect(jsonPath("$.[*].rebotes").value(hasItem(DEFAULT_REBOTES)))
                .andExpect(jsonPath("$.[*].faltas").value(hasItem(DEFAULT_FALTAS)));
    }

    @Test
    @Transactional
    public void getJugadorJuegaPartido() throws Exception {
        // Initialize the database
        jugadorJuegaPartidoRepository.saveAndFlush(jugadorJuegaPartido);

        // Get the jugadorJuegaPartido
        restJugadorJuegaPartidoMockMvc.perform(get("/api/jugadorJuegaPartidos/{id}", jugadorJuegaPartido.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(jugadorJuegaPartido.getId().intValue()))
            .andExpect(jsonPath("$.canastas").value(DEFAULT_CANASTAS))
            .andExpect(jsonPath("$.asistencias").value(DEFAULT_ASISTENCIAS))
            .andExpect(jsonPath("$.rebotes").value(DEFAULT_REBOTES))
            .andExpect(jsonPath("$.faltas").value(DEFAULT_FALTAS));
    }

    @Test
    @Transactional
    public void getNonExistingJugadorJuegaPartido() throws Exception {
        // Get the jugadorJuegaPartido
        restJugadorJuegaPartidoMockMvc.perform(get("/api/jugadorJuegaPartidos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJugadorJuegaPartido() throws Exception {
        // Initialize the database
        jugadorJuegaPartidoRepository.saveAndFlush(jugadorJuegaPartido);

		int databaseSizeBeforeUpdate = jugadorJuegaPartidoRepository.findAll().size();

        // Update the jugadorJuegaPartido
        jugadorJuegaPartido.setCanastas(UPDATED_CANASTAS);
        jugadorJuegaPartido.setAsistencias(UPDATED_ASISTENCIAS);
        jugadorJuegaPartido.setRebotes(UPDATED_REBOTES);
        jugadorJuegaPartido.setFaltas(UPDATED_FALTAS);

        restJugadorJuegaPartidoMockMvc.perform(put("/api/jugadorJuegaPartidos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jugadorJuegaPartido)))
                .andExpect(status().isOk());

        // Validate the JugadorJuegaPartido in the database
        List<JugadorJuegaPartido> jugadorJuegaPartidos = jugadorJuegaPartidoRepository.findAll();
        assertThat(jugadorJuegaPartidos).hasSize(databaseSizeBeforeUpdate);
        JugadorJuegaPartido testJugadorJuegaPartido = jugadorJuegaPartidos.get(jugadorJuegaPartidos.size() - 1);
        assertThat(testJugadorJuegaPartido.getCanastas()).isEqualTo(UPDATED_CANASTAS);
        assertThat(testJugadorJuegaPartido.getAsistencias()).isEqualTo(UPDATED_ASISTENCIAS);
        assertThat(testJugadorJuegaPartido.getRebotes()).isEqualTo(UPDATED_REBOTES);
        assertThat(testJugadorJuegaPartido.getFaltas()).isEqualTo(UPDATED_FALTAS);
    }

    @Test
    @Transactional
    public void deleteJugadorJuegaPartido() throws Exception {
        // Initialize the database
        jugadorJuegaPartidoRepository.saveAndFlush(jugadorJuegaPartido);

		int databaseSizeBeforeDelete = jugadorJuegaPartidoRepository.findAll().size();

        // Get the jugadorJuegaPartido
        restJugadorJuegaPartidoMockMvc.perform(delete("/api/jugadorJuegaPartidos/{id}", jugadorJuegaPartido.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<JugadorJuegaPartido> jugadorJuegaPartidos = jugadorJuegaPartidoRepository.findAll();
        assertThat(jugadorJuegaPartidos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
