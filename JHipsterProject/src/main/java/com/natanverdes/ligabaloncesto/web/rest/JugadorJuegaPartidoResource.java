package com.natanverdes.ligabaloncesto.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.natanverdes.ligabaloncesto.domain.JugadorJuegaPartido;
import com.natanverdes.ligabaloncesto.repository.JugadorJuegaPartidoRepository;
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
 * REST controller for managing JugadorJuegaPartido.
 */
@RestController
@RequestMapping("/api")
public class JugadorJuegaPartidoResource {

    private final Logger log = LoggerFactory.getLogger(JugadorJuegaPartidoResource.class);

    @Inject
    private JugadorJuegaPartidoRepository jugadorJuegaPartidoRepository;

    /**
     * POST  /jugadorJuegaPartidos -> Create a new jugadorJuegaPartido.
     */
    @RequestMapping(value = "/jugadorJuegaPartidos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JugadorJuegaPartido> createJugadorJuegaPartido(@RequestBody JugadorJuegaPartido jugadorJuegaPartido) throws URISyntaxException {
        log.debug("REST request to save JugadorJuegaPartido : {}", jugadorJuegaPartido);
        if (jugadorJuegaPartido.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new jugadorJuegaPartido cannot already have an ID").body(null);
        }
        JugadorJuegaPartido result = jugadorJuegaPartidoRepository.save(jugadorJuegaPartido);
        return ResponseEntity.created(new URI("/api/jugadorJuegaPartidos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("jugadorJuegaPartido", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jugadorJuegaPartidos -> Updates an existing jugadorJuegaPartido.
     */
    @RequestMapping(value = "/jugadorJuegaPartidos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JugadorJuegaPartido> updateJugadorJuegaPartido(@RequestBody JugadorJuegaPartido jugadorJuegaPartido) throws URISyntaxException {
        log.debug("REST request to update JugadorJuegaPartido : {}", jugadorJuegaPartido);
        if (jugadorJuegaPartido.getId() == null) {
            return createJugadorJuegaPartido(jugadorJuegaPartido);
        }
        JugadorJuegaPartido result = jugadorJuegaPartidoRepository.save(jugadorJuegaPartido);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("jugadorJuegaPartido", jugadorJuegaPartido.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jugadorJuegaPartidos -> get all the jugadorJuegaPartidos.
     */
    @RequestMapping(value = "/jugadorJuegaPartidos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<JugadorJuegaPartido>> getAllJugadorJuegaPartidos(Pageable pageable)
        throws URISyntaxException {
        Page<JugadorJuegaPartido> page = jugadorJuegaPartidoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jugadorJuegaPartidos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /jugadorJuegaPartidos/:id -> get the "id" jugadorJuegaPartido.
     */
    @RequestMapping(value = "/jugadorJuegaPartidos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JugadorJuegaPartido> getJugadorJuegaPartido(@PathVariable Long id) {
        log.debug("REST request to get JugadorJuegaPartido : {}", id);
        return Optional.ofNullable(jugadorJuegaPartidoRepository.findOne(id))
            .map(jugadorJuegaPartido -> new ResponseEntity<>(
                jugadorJuegaPartido,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /jugadorJuegaPartidos/:id -> delete the "id" jugadorJuegaPartido.
     */
    @RequestMapping(value = "/jugadorJuegaPartidos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteJugadorJuegaPartido(@PathVariable Long id) {
        log.debug("REST request to delete JugadorJuegaPartido : {}", id);
        jugadorJuegaPartidoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("jugadorJuegaPartido", id.toString())).build();
    }
}
