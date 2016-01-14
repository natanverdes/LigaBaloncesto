package com.natanverdes.Repository;

import com.natanverdes.Model.Jugador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JugadorRepository extends PagingAndSortingRepository<Jugador, Long>{
//     Ej1.A - Buscar jugadores por nombre, de manera que no sea necesario introducir el nombre completo.

//     StartingWith: Que empiece por...
    List<Jugador> findByNombreStartingWith(String nombre);


//     Ej1.B - Buscar jugadores que hayan conseguido un número mayor o igual a un número de canastas especificado
//     como parámetro.

//     GreaterThanEqual: Mayor o igual a...
    List<Jugador> findByCanastasGreaterThanEqual(int numeroCanastasTotales);


//     Ej1.C - Buscar jugadores que hayan efectuado un número de asistencias dentro de un rango especificado como parámetro
//     (El rango se específica mediante un valor mínimo y un valor máximo).

//     Between: Entre...
    List<Jugador> findByAsistenciasBetween(int minimo, int maximo);


//     Ej1.D - Buscar jugadores que pertenezcan a una posición específica, por ejemplo: base

//     void or Is: Que contengan exactamente...
    List<Jugador> findByPosicion(String posicion);


//     Ej1.E - Buscar jugadores que hayan nacido en una fecha anterior a una fecha especificada como parámetro.

//     LessThan: Menor que...
    List<Jugador> findByFechaNacimientoBefore(Date fecha);


//     Ej1.F - Combinar los apartados B y E: es decir, la consulta ha de devolver los jugadores que hayan conseguido
//     un número total de canastas mayor o igual a un parámetro, y además que he nacido en una fecha anterior a
//     una fecha especificada como parámetro.

//     And: Y...
    List<Jugador> findByCanastasGreaterThanEqualAndFechaNacimientoBefore(int canastas, Date fechaNacimiento);




//    Ej2.B Devuelve todos los jugadores de un equipo, a partir del nombre completo del equipo.

//    Se utiliza _ para definir explícitamente que se trata de una propiedad del objeto al que le precede.
//    Solo es necesario cuando existen variables de varias palabras que puedan crear ambiguedad en la selección.

    List<Jugador> findByEquipo_Nombre(String equipoNombre);


//    Ej2.C Devuelve todos los jugadores de un equipo, que además jueguen en la misma posición, por ejemplo, alero.
    List<Jugador> findByEquipoNombreAndPosicion(String equipoNombre, String posicion);


//    Ej2.D Devuelve el jugador que más canastas ha conseguido del total de jugadores

//    findFirstBy: Solo localiza el primero de la selección. Importante poner al final Asc o Desc.
    Jugador findFirstByOrderByCanastasDesc();


//    Ej2.E Devuelve los cinco jugadores que más asistencias han efectuado

//    findFirstBy5: Solo localiza los 5 primeros de la selección
    List<Jugador> findFirst5ByOrderByAsistenciasDesc();


//    Ej2.F Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.

//    Con la anotación @Query podemos definir una consulta personalizada con nombre personalizado usando JPQL.
//    En este caso no nos hace falta el @Query obligatoriamente. Podríamos usar findByEquipoNombreOrderByCanastasDesc
    @Query("SELECT j FROM Jugador j WHERE j.equipo.nombre = :equipoNombre ORDER BY j.canastas DESC")
    List<Jugador> findByEquipoNombreCanastasDesc(@Param("equipoNombre") String equipoNombre);
}
