package com.natanverdes.ligabaloncesto.repository;

import com.natanverdes.ligabaloncesto.domain.Liga;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Liga entity.
 */
public interface LigaRepository extends JpaRepository<Liga,Long> {

}
