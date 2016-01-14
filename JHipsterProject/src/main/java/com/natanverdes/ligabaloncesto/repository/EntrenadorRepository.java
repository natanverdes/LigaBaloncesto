package com.natanverdes.ligabaloncesto.repository;

import com.natanverdes.ligabaloncesto.domain.Entrenador;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Entrenador entity.
 */
public interface EntrenadorRepository extends JpaRepository<Entrenador,Long> {

}
