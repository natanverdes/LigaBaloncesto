package com.natanverdes.ligabaloncesto.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.natanverdes.ligabaloncesto.domain.Temporada;
import com.natanverdes.ligabaloncesto.repository.TemporadaRepository;
import com.natanverdes.ligabaloncesto.web.rest.util.HeaderUtil;
import com.natanverdes.ligabaloncesto.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Temporada.
 */
@RestController
@RequestMapping("/api")
public class TemporadaResource {

    private final Logger log = LoggerFactory.getLogger(TemporadaResource.class);

    @Inject
    private TemporadaRepository temporadaRepository;

    /**
     * POST  /temporadas -> Create a new temporada.
     */
    @RequestMapping(value = "/temporadas",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Temporada> createTemporada(@RequestBody Temporada temporada) throws URISyntaxException {
        log.debug("REST request to save Temporada : {}", temporada);
        if (temporada.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new temporada cannot already have an ID").body(null);
        }
        Temporada result = temporadaRepository.save(temporada);
        return ResponseEntity.created(new URI("/api/temporadas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("temporada", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /temporadas -> Updates an existing temporada.
     */
    @RequestMapping(value = "/temporadas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Temporada> updateTemporada(@RequestBody Temporada temporada) throws URISyntaxException {
        log.debug("REST request to update Temporada : {}", temporada);
        if (temporada.getId() == null) {
            return createTemporada(temporada);
        }
        Temporada result = temporadaRepository.save(temporada);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("temporada", temporada.getId().toString()))
            .body(result);
    }

    /**
     * GET  /temporadas -> get all the temporadas.
     */
    @RequestMapping(value = "/temporadas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Temporada>> getAllTemporadas(Pageable pageable)
        throws URISyntaxException {
        Page<Temporada> page = temporadaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/temporadas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /temporadas/:id -> get the "id" temporada.
     */
    @RequestMapping(value = "/temporadas/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Temporada> getTemporada(@PathVariable Long id) {
        log.debug("REST request to get Temporada : {}", id);
        return Optional.ofNullable(temporadaRepository.findOne(id))
            .map(temporada -> new ResponseEntity<>(
                temporada,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /temporadas/:id -> delete the "id" temporada.
     */
    @RequestMapping(value = "/temporadas/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTemporada(@PathVariable Long id) {
        log.debug("REST request to delete Temporada : {}", id);
        temporadaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("temporada", id.toString())).build();
    }
}
