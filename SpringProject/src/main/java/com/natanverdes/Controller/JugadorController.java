package com.natanverdes.Controller;

import com.natanverdes.Model.Jugador;
import com.natanverdes.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

// @RestController: Decimos que esto es un controlador
// @RequestMapping: Definimos la ruta del recurso
@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorRepository jugadorRepository;

//    @RequestMapping: lo utilizamos para definir que esta funcion
//      se utilizará para el tipo de peticiones definido por "(method = RequestMethod.GET)"
//    Podemos utilizar GET, POST, PUT y DELETE

//    Devuelve un listado de todos los jugadores
//    URL: GET http://192.168.27.50:8080/jugadores
    @RequestMapping(method = RequestMethod.GET)
    public List<Jugador> findAll() {
        System.out.println("Petición REST de Jugador findAll");
        List<Jugador> jugadores = new ArrayList<>();
        for (Jugador jugador : jugadorRepository.findAll()) {
            jugadores.add(jugador);
        }
        return jugadores;
    }

//    @RequestMapping(params = {"one", "two", [...]}): lo utilizaremos para definir
//      que esa función se utilizará explícitamente en caso de que lleven esos parámetros.
//    Esto permite utilizar distintas funciones en una misma URL

//    @RequestParam(value = "nombreEquipo", required = true): permite definir un parámetro
//      tangible para nuestra función Java que será enviado por la petición GET
//    "value" lo utilizamos para definir el nombre de ese parámetro,
//      y "required" para definir si este es requerido o no


//    Devuelve un listado de todos los jugadores que tengan el nombre de equipo especificado
//    URL Ejemplo: GET http://192.168.27.50:8080/jugadores?nombreEquipo=FC%20Barcelona
    @RequestMapping(method = RequestMethod.GET, params = "nombreEquipo")
    public List<Jugador> findAllByEquipo(
            @RequestParam(value = "nombreEquipo", required = true) String nombreEquipo
    ){
        System.out.println("Petición REST de Jugador findAllByEquipo");
        return jugadorRepository.findByEquipo_Nombre(nombreEquipo);
    }

//    @ResponseStatus: Define la respuesta que daremos por HTTP
//    Podemos utilizarlo para definir que está correcto, p. ej.: OK, CREATED, NO_CONTENT
//      O que se trata de un error, p. ej.: BAD_REQUEST, UNAUTHORIZED, FORBIDDEN, NOT_FOUND, CONFLICT

//    @RequestBody: Define un parámetro tangible para nuestra función
//      enviado en el Body de nuestra petición POST.
//    Al poner un objeto como parámetro, esta variable tendrá que enviarse en
//      formato JSON tal y como nosotros se la enviamos por GET.

//    Permite crear un Jugador en nuestra base de datos
//    URL: POST http://192.168.27.50:8080/jugadores
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Jugador create(@RequestBody Jugador jugador) {
        System.out.println("Petición REST de Jugador create");
        return jugadorRepository.save(jugador);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT): permite definir que esta función
//      se utilizará en caso de peticiones PUT, y permite añadir una variable por parámetro ("/{id}")

//    @PathVariable: Nos deja utilizar una variable de dentro de la URL como parámetro.
//      debe haberse utilizado anteriormente el @RequestMapping(value = "/{variable}")

//    Permite editar un jugador con una ID específica
//    URL: PUT http://192.168.27.50:8080/jugadores/1
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Jugador updateById(@PathVariable Long id, @RequestBody Jugador jugador) {
        System.out.println("Petición REST de Jugador updateById");
        Jugador jugadorBuscado = jugadorRepository.findOne(id);

        if(jugadorBuscado == null){return new Jugador();}

        return jugadorRepository.save(jugador);
    }
//    Permite eliminar un jugador con una ID específica
//    URL: DELETE http://192.168.27.50:8080/jugadores/1
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        System.out.println("Petición REST de Jugador deleteById");
        Jugador jugador = jugadorRepository.findOne(id);
        if(jugador != null){jugadorRepository.delete(id);}
    }
}
