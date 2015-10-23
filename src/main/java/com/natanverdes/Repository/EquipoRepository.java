package com.natanverdes.Repository;

import com.natanverdes.Model.Equipo;
import com.natanverdes.Model.Jugador;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface EquipoRepository extends PagingAndSortingRepository<Equipo, Long>{
//    Ej2.A Consulta los equipos existentes en una localidad determinada.
    List<Equipo> findByLocalidad(String localidad);
}
