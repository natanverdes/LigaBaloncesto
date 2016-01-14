package com.natanverdes.ligabaloncesto.repository;

import com.natanverdes.ligabaloncesto.domain.Equipo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Equipo entity.
 */
public interface EquipoRepository extends JpaRepository<Equipo,Long> {

    @Query("select distinct equipo from Equipo equipo left join fetch equipo.temporadass")
    List<Equipo> findAllWithEagerRelationships();

    @Query("select equipo from Equipo equipo left join fetch equipo.temporadass where equipo.id =:id")
    Equipo findOneWithEagerRelationships(@Param("id") Long id);

}
