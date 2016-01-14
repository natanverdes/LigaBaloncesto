package com.natanverdes.ligabaloncesto.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.natanverdes.ligabaloncesto.domain.Arbitro;
import com.natanverdes.ligabaloncesto.repository.ArbitroRepository;
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
 * REST controller for managing Arbitro.
 */
@RestController
@RequestMapping("/api")
public class ArbitroResource {

    private final Logger log = LoggerFactory.getLogger(ArbitroResource.class);

    @Inject
    private ArbitroRepository arbitroRepository;

    /**
     * POST  /arbitros -> Create a new arbitro.
     */
    @RequestMapping(value = "/arbitros",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Arbitro> createArbitro(@RequestBody Arbitro arbitro) throws URISyntaxException {
        log.debug("REST request to save Arbitro : {}", arbitro);
        if (arbitro.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new arbitro cannot already have an ID").body(null);
        }
        Arbitro result = arbitroRepository.save(arbitro);
        return ResponseEntity.created(new URI("/api/arbitros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("arbitro", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /arbitros -> Updates an existing arbitro.
     */
    @RequestMapping(value = "/arbitros",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Arbitro> updateArbitro(@RequestBody Arbitro arbitro) throws URISyntaxException {
        log.debug("REST request to update Arbitro : {}", arbitro);
        if (arbitro.getId() == null) {
            return createArbitro(arbitro);
        }
        Arbitro result = arbitroRepository.save(arbitro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("arbitro", arbitro.getId().toString()))
            .body(result);
    }

    /**
     * GET  /arbitros -> get all the arbitros.
     */
    @RequestMapping(value = "/arbitros",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Arbitro>> getAllArbitros(Pageable pageable)
        throws URISyntaxException {
        Page<Arbitro> page = arbitroRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/arbitros");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /arbitros/:id -> get the "id" arbitro.
     */
    @RequestMapping(value = "/arbitros/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Arbitro> getArbitro(@PathVariable Long id) {
        log.debug("REST request to get Arbitro : {}", id);
        return Optional.ofNullable(arbitroRepository.findOne(id))
            .map(arbitro -> new ResponseEntity<>(
                arbitro,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /arbitros/:id -> delete the "id" arbitro.
     */
    @RequestMapping(value = "/arbitros/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteArbitro(@PathVariable Long id) {
        log.debug("REST request to delete Arbitro : {}", id);
        arbitroRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("arbitro", id.toString())).build();
    }
}
