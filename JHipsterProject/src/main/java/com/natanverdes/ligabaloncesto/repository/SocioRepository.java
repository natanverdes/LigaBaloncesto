package com.natanverdes.ligabaloncesto.repository;

import com.natanverdes.ligabaloncesto.domain.Socio;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Socio entity.
 */
public interface SocioRepository extends JpaRepository<Socio,Long> {

    @Query("select distinct socio from Socio socio left join fetch socio.equiposEsSocios")
    List<Socio> findAllWithEagerRelationships();

    @Query("select socio from Socio socio left join fetch socio.equiposEsSocios where socio.id =:id")
    Socio findOneWithEagerRelationships(@Param("id") Long id);

}
