package com.natanverdes.Service;

import com.natanverdes.Model.Jugador;
import com.natanverdes.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Transactional
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;

    public void testJugador(){

//        Creamos el objeto calendario para luego insertarlo en el jugador
        Calendar date = GregorianCalendar.getInstance();

//        Creamos los objetos de prueba
        date.set(1980, Calendar.JUNE, 13);
        Jugador jugador1 = new Jugador(
                "Juan Carlos Navarro",
                date.getTime(),
                50, 150, 200, "Escolta");

        date.set(1987, Calendar.MARCH, 2);
        Jugador jugador2 = new Jugador(
                "Pau Ribas i Tossas",
                date.getTime(),
                80, 180, 220, "Escolta");

        date.set(1984, Calendar.AUGUST, 7);
        Jugador jugador3 = new Jugador(
                "Stratos Perperoglou",
                date.getTime(),
                120, 220, 260, "Alero");

        date.set(1979, Calendar.JULY, 30);
        Jugador jugador4 = new Jugador(
                "Carlos Arroyo",
                date.getTime(),
                140, 240, 300, "Base");

        date.set(1987, Calendar.FEBRUARY, 17);
        Jugador jugador5 = new Jugador(
                "Ante Tomic",
                date.getTime(),
                200, 300, 400, "Pívot");

//        Guardamos los jugadores y los insertamos en la base de datos
        jugadorRepository.save(jugador1);
        jugadorRepository.save(jugador2);
        jugadorRepository.save(jugador3);
        jugadorRepository.save(jugador4);
        jugadorRepository.save(jugador5);

//        Imprimimos los selects de prueba, recogidos en el repository
        System.out.println("findByNombreStartingWith: " + jugadorRepository.findByNombreStartingWith("A"));

        System.out.println("findByNumeroCanastasTotalesGreaterThanEqual: " + jugadorRepository.findByNumeroCanastasTotalesGreaterThanEqual(100));

        System.out.println("findByNumeroAsistenciasTotalesBetween: " + jugadorRepository.findByNumeroAsistenciasTotalesBetween(170, 230));

        System.out.println("findByPosicionEnElCampoPorDefecto: " + jugadorRepository.findByPosicionEnElCampoPorDefecto("Escolta"));

        date.set(1980, Calendar.DECEMBER, 27);
        System.out.println("findByFechaNacimientoBefore: " + jugadorRepository.findByFechaNacimientoBefore(date.getTime()));

        date.set(1984, Calendar.AUGUST, 10);
        System.out.println("findByNumeroCanastasTotalesGreaterThanEqualAndFechaNacimientoBefore: " + jugadorRepository.findByNumeroCanastasTotalesGreaterThanEqualAndFechaNacimientoBefore(120, date.getTime()));

    }
}
