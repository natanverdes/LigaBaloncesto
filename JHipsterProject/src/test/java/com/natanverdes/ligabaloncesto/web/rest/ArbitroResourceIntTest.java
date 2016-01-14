package com.natanverdes.ligabaloncesto.web.rest;

import com.natanverdes.ligabaloncesto.Application;
import com.natanverdes.ligabaloncesto.domain.Arbitro;
import com.natanverdes.ligabaloncesto.repository.ArbitroRepository;

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
 * Test class for the ArbitroResource REST controller.
 *
 * @see ArbitroResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ArbitroResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAA";
    private static final String UPDATED_NOMBRE = "BBBBB";

    @Inject
    private ArbitroRepository arbitroRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restArbitroMockMvc;

    private Arbitro arbitro;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ArbitroResource arbitroResource = new ArbitroResource();
        ReflectionTestUtils.setField(arbitroResource, "arbitroRepository", arbitroRepository);
        this.restArbitroMockMvc = MockMvcBuilders.standaloneSetup(arbitroResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        arbitro = new Arbitro();
        arbitro.setNombre(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createArbitro() throws Exception {
        int databaseSizeBeforeCreate = arbitroRepository.findAll().size();

        // Create the Arbitro

        restArbitroMockMvc.perform(post("/api/arbitros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(arbitro)))
                .andExpect(status().isCreated());

        // Validate the Arbitro in the database
        List<Arbitro> arbitros = arbitroRepository.findAll();
        assertThat(arbitros).hasSize(databaseSizeBeforeCreate + 1);
        Arbitro testArbitro = arbitros.get(arbitros.size() - 1);
        assertThat(testArbitro.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllArbitros() throws Exception {
        // Initialize the database
        arbitroRepository.saveAndFlush(arbitro);

        // Get all the arbitros
        restArbitroMockMvc.perform(get("/api/arbitros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(arbitro.getId().intValue())))
                .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())));
    }

    @Test
    @Transactional
    public void getArbitro() throws Exception {
        // Initialize the database
        arbitroRepository.saveAndFlush(arbitro);

        // Get the arbitro
        restArbitroMockMvc.perform(get("/api/arbitros/{id}", arbitro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(arbitro.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArbitro() throws Exception {
        // Get the arbitro
        restArbitroMockMvc.perform(get("/api/arbitros/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArbitro() throws Exception {
        // Initialize the database
        arbitroRepository.saveAndFlush(arbitro);

		int databaseSizeBeforeUpdate = arbitroRepository.findAll().size();

        // Update the arbitro
        arbitro.setNombre(UPDATED_NOMBRE);

        restArbitroMockMvc.perform(put("/api/arbitros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(arbitro)))
                .andExpect(status().isOk());

        // Validate the Arbitro in the database
        List<Arbitro> arbitros = arbitroRepository.findAll();
        assertThat(arbitros).hasSize(databaseSizeBeforeUpdate);
        Arbitro testArbitro = arbitros.get(arbitros.size() - 1);
        assertThat(testArbitro.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void deleteArbitro() throws Exception {
        // Initialize the database
        arbitroRepository.saveAndFlush(arbitro);

		int databaseSizeBeforeDelete = arbitroRepository.findAll().size();

        // Get the arbitro
        restArbitroMockMvc.perform(delete("/api/arbitros/{id}", arbitro.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Arbitro> arbitros = arbitroRepository.findAll();
        assertThat(arbitros).hasSize(databaseSizeBeforeDelete - 1);
    }
}
