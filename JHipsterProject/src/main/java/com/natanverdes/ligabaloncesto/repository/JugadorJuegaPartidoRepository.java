package com.natanverdes.ligabaloncesto.repository;

import com.natanverdes.ligabaloncesto.domain.JugadorJuegaPartido;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the JugadorJuegaPartido entity.
 */
public interface JugadorJuegaPartidoRepository extends JpaRepository<JugadorJuegaPartido,Long> {

}
