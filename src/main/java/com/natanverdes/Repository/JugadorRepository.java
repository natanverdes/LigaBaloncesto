package com.natanverdes.Repository;

import com.natanverdes.Model.Jugador;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface JugadorRepository extends PagingAndSortingRepository<Jugador, Long>{
//     A - Buscar jugadores por nombre, de manera que no sea necesario introducir el nombre completo.
//     StartingWith: Que empiece por...
    List<Jugador> findByNombreStartingWith(String nombre);

//     B - Buscar jugadores que hayan conseguido un número mayor o igual a un número de canastas especificado como parámetro.
//     GreaterThanEqual: Mayor o igual a...
    List<Jugador> findByNumeroCanastasTotalesGreaterThanEqual(int numeroCanastasTotales);

//     C - Buscar jugadores que hayan efectuado un número de asistencias dentro de un rango especificado como parámetro
//     (El rango se específica mediante un valor mínimo y un valor máximo).
//     Between: Entre...
    List<Jugador> findByNumeroAsistenciasTotalesBetween(int minimo, int maximo);

//     D - Buscar jugadores que pertenezcan a una posición específica, por ejemplo: base
//     void or Is: Que contengan exactamente...
    List<Jugador> findByPosicionEnElCampoPorDefecto(String posicion);

//     E - Buscar jugadores que hayan nacido en una fecha anterior a una fecha especificada como parámetro.
//     LessThan: Menor que...
    List<Jugador> findByFechaNacimientoBefore(Date fecha);

//     F - Combinar los apartados B y E: es decir, la consulta ha de devolver los jugadores que hayan conseguido
//     un número total de canastas mayor o igual a un parámetro, y además que he nacido en una fecha anterior a
//     una fecha especificada como parámetro.
//     And: Y...
    List<Jugador> findByNumeroCanastasTotalesGreaterThanEqualAndFechaNacimientoBefore(int canastas, Date fechaNacimiento);
}
