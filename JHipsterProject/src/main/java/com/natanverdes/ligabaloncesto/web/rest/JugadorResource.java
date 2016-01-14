package com.natanverdes.ligabaloncesto.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.natanverdes.ligabaloncesto.domain.Jugador;
import com.natanverdes.ligabaloncesto.repository.JugadorRepository;
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
 * REST controller for managing Jugador.
 */
@RestController
@RequestMapping("/api")
public class JugadorResource {

    private final Logger log = LoggerFactory.getLogger(JugadorResource.class);

    @Inject
    private JugadorRepository jugadorRepository;

    /**
     * POST  /jugadors -> Create a new jugador.
     */
    @RequestMapping(value = "/jugadors",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) throws URISyntaxException {
        log.debug("REST request to save Jugador : {}", jugador);
        if (jugador.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new jugador cannot already have an ID").body(null);
        }
        Jugador result = jugadorRepository.save(jugador);
        return ResponseEntity.created(new URI("/api/jugadors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("jugador", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jugadors -> Updates an existing jugador.
     */
    @RequestMapping(value = "/jugadors",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Jugador> updateJugador(@RequestBody Jugador jugador) throws URISyntaxException {
        log.debug("REST request to update Jugador : {}", jugador);
        if (jugador.getId() == null) {
            return createJugador(jugador);
        }
        Jugador result = jugadorRepository.save(jugador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("jugador", jugador.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jugadors -> get all the jugadors.
     */
    @RequestMapping(value = "/jugadors",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Jugador>> getAllJugadors(Pageable pageable)
        throws URISyntaxException {
        Page<Jugador> page = jugadorRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jugadors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /jugadors/:id -> get the "id" jugador.
     */
    @RequestMapping(value = "/jugadors/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Jugador> getJugador(@PathVariable Long id) {
        log.debug("REST request to get Jugador : {}", id);
        return Optional.ofNullable(jugadorRepository.findOne(id))
            .map(jugador -> new ResponseEntity<>(
                jugador,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /jugadors/:id -> delete the "id" jugador.
     */
    @RequestMapping(value = "/jugadors/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
        log.debug("REST request to delete Jugador : {}", id);
        jugadorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("jugador", id.toString())).build();
    }
}
