package com.natanverdes.ligabaloncesto.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.natanverdes.ligabaloncesto.domain.Entrenador;
import com.natanverdes.ligabaloncesto.repository.EntrenadorRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Entrenador.
 */
@RestController
@RequestMapping("/api")
public class EntrenadorResource {

    private final Logger log = LoggerFactory.getLogger(EntrenadorResource.class);

    @Inject
    private EntrenadorRepository entrenadorRepository;

    /**
     * POST  /entrenadors -> Create a new entrenador.
     */
    @RequestMapping(value = "/entrenadors",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Entrenador> createEntrenador(@RequestBody Entrenador entrenador) throws URISyntaxException {
        log.debug("REST request to save Entrenador : {}", entrenador);
        if (entrenador.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new entrenador cannot already have an ID").body(null);
        }
        Entrenador result = entrenadorRepository.save(entrenador);
        return ResponseEntity.created(new URI("/api/entrenadors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("entrenador", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entrenadors -> Updates an existing entrenador.
     */
    @RequestMapping(value = "/entrenadors",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Entrenador> updateEntrenador(@RequestBody Entrenador entrenador) throws URISyntaxException {
        log.debug("REST request to update Entrenador : {}", entrenador);
        if (entrenador.getId() == null) {
            return createEntrenador(entrenador);
        }
        Entrenador result = entrenadorRepository.save(entrenador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("entrenador", entrenador.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entrenadors -> get all the entrenadors.
     */
    @RequestMapping(value = "/entrenadors",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Entrenador>> getAllEntrenadors(Pageable pageable, @RequestParam(required = false) String filter)
        throws URISyntaxException {
        if ("equipo-is-null".equals(filter)) {
            log.debug("REST request to get all Entrenadors where equipo is null");
            return new ResponseEntity<>(StreamSupport
                .stream(entrenadorRepository.findAll().spliterator(), false)
                .filter(entrenador -> entrenador.getEquipo() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        
        Page<Entrenador> page = entrenadorRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entrenadors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /entrenadors/:id -> get the "id" entrenador.
     */
    @RequestMapping(value = "/entrenadors/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Entrenador> getEntrenador(@PathVariable Long id) {
        log.debug("REST request to get Entrenador : {}", id);
        return Optional.ofNullable(entrenadorRepository.findOne(id))
            .map(entrenador -> new ResponseEntity<>(
                entrenador,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /entrenadors/:id -> delete the "id" entrenador.
     */
    @RequestMapping(value = "/entrenadors/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEntrenador(@PathVariable Long id) {
        log.debug("REST request to delete Entrenador : {}", id);
        entrenadorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("entrenador", id.toString())).build();
    }
}
