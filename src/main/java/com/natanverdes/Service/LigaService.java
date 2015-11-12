package com.natanverdes.Service;

import com.natanverdes.Model.Equipo;
import com.natanverdes.Model.Jugador;
import com.natanverdes.Model.Liga;
import com.natanverdes.Model.Temporada;
import com.natanverdes.Repository.EquipoRepository;
import com.natanverdes.Repository.JugadorRepository;
import com.natanverdes.Repository.LigaRepository;
import com.natanverdes.Repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class LigaService {
    @Autowired
    private LigaRepository ligaRepository;

    @Autowired
    private TemporadaRepository temporadaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public void crearEntidades(){
//        -----------------
//        ------ LIGA -----
//        -----------------
        Liga liga1 = new Liga("ACB");
        ligaRepository.save(liga1);

//        ----------------------
//        ------ TEMPORADA -----
//        ----------------------
        Temporada temporada1 = new Temporada("ACB-2014", liga1);
        Temporada temporada2 = new Temporada("ACB-2015", liga1);
        temporadaRepository.save(temporada1);
        temporadaRepository.save(temporada2);

//        -------------------
//        ------ EQUIPO -----
//        -------------------

//        Creamos el objeto Calendar para guardar la fecha en las entidades
        Calendar date = GregorianCalendar.getInstance();

        date.set(1980, Calendar.JANUARY, 1);
        Equipo equipo1 = new Equipo("FC Barcelona", "Barcelona", date.getTime());
        equipo1.getTemporadasJugadas().add(temporada1);
        equipo1.getTemporadasJugadas().add(temporada2);
        equipoRepository.save(equipo1);

        date.set(1986, Calendar.FEBRUARY, 2);
        Equipo equipo2 = new Equipo("Valencia Basket Club", "Valencia", date.getTime());
        equipo2.getTemporadasJugadas().add(temporada2);
        equipoRepository.save(equipo2);

        date.set(1977, Calendar.MARCH, 5);
        Equipo equipo3 = new Equipo("Unicaja", "Malaga", date.getTime());
        equipo3.getTemporadasJugadas().add(temporada2);
        equipoRepository.save(equipo3);

        date.set(1963, Calendar.APRIL, 10);
        Equipo equipo4 = new Equipo("Herbalife Gran Canaria", "Canarias", date.getTime());
        equipo4.getTemporadasJugadas().add(temporada2);
        equipoRepository.save(equipo4);

        date.set(1959, Calendar.AUGUST, 20);
        Equipo equipo5 = new Equipo("Laboral Kutxa Baskonia", "Vitoria-Gasteiz", date.getTime());
        equipo5.getTemporadasJugadas().add(temporada2);
        equipoRepository.save(equipo5);

//        -----------------
//        ---- JUGADOR ----
//        -----------------

//        JUGADORES DE EQUIPO 1
        date.set(1980, Calendar.JUNE, 13);
        Jugador jugador1 = new Jugador(
                "Juan Carlos Navarro",
                date.getTime(),
                38, 136, 342, "Escolta", equipo1);
        jugadorRepository.save(jugador1);

        date.set(1987, Calendar.MARCH, 2);
        Jugador jugador2 = new Jugador(
                "Pau Ribas i Tossas",
                date.getTime(),
                80, 180, 220, "Escolta", equipo1);
        jugadorRepository.save(jugador2);

        date.set(1984, Calendar.AUGUST, 7);
        Jugador jugador3 = new Jugador(
                "Stratos Perperoglou",
                date.getTime(),
                120, 220, 260, "Alero", equipo1);
        jugadorRepository.save(jugador3);

        date.set(1979, Calendar.JULY, 30);
        Jugador jugador4 = new Jugador(
                "Carlos Arroyo",
                date.getTime(),
                140, 240, 300, "Base", equipo1);
        jugadorRepository.save(jugador4);

        date.set(1987, Calendar.FEBRUARY, 17);
        Jugador jugador5 = new Jugador(
                "Ante Tomic",
                date.getTime(),
                200, 300, 400, "Pívot", equipo1);
        jugadorRepository.save(jugador5);

//        JUGADORES DE EQUIPO 2
        date.set(1980, Calendar.JUNE, 22);
        Jugador jugador6 = new Jugador(
                "Juanjo Triguero",
                date.getTime(),
                23, 142, 187, "Pívot", equipo2);
        jugadorRepository.save(jugador6);

        date.set(1981, Calendar.MARCH, 12);
        Jugador jugador7 = new Jugador(
                "Rafa Martínez",
                date.getTime(),
                88, 142, 261, "Escolta", equipo2);
        jugadorRepository.save(jugador7);

        date.set(1982, Calendar.APRIL, 23);
        Jugador jugador8 = new Jugador(
                "Justin Doellman",
                date.getTime(),
                100, 163, 261, "Ala-Pívot", equipo2);
        jugadorRepository.save(jugador8);

        date.set(1983, Calendar.JULY, 23);
        Jugador jugador9 = new Jugador(
                "Sam Van Rossom",
                date.getTime(),
                22, 159, 321, "Base", equipo2);
        jugadorRepository.save(jugador9);

        date.set(1984, Calendar.JANUARY, 17);
        Jugador jugador10 = new Jugador(
                "Romain Sato",
                date.getTime(),
                123, 283, 316, "Alero", equipo2);
        jugadorRepository.save(jugador10);

//        JUGADORES DE EQUIPO 3
        date.set(1994, Calendar.APRIL, 23);
        Jugador jugador11 = new Jugador(
                "Alberto Díaz",
                date.getTime(),
                37, 147, 627, "Base", equipo3);
        jugadorRepository.save(jugador11);

        date.set(1986, Calendar.NOVEMBER, 15);
        Jugador jugador12 = new Jugador(
                "Richard Hendrix",
                date.getTime(),
                75, 125, 241, "Pívot", equipo3);
        jugadorRepository.save(jugador12);

        date.set(1988, Calendar.APRIL, 25);
        Jugador jugador13 = new Jugador(
                "Stefan Markovic",
                date.getTime(),
                173, 182, 212, "Base", equipo3);
        jugadorRepository.save(jugador13);

        date.set(1983, Calendar.MAY, 1);
        Jugador jugador14 = new Jugador(
                "Fran Vázquez",
                date.getTime(),
                35, 132, 216, "Pívot", equipo3);
        jugadorRepository.save(jugador14);

        date.set(1987, Calendar.APRIL, 7);
        Jugador jugador15 = new Jugador(
                "Jamar Smith",
                date.getTime(),
                162, 362, 411, "Escolta", equipo3);
        jugadorRepository.save(jugador15);

//        JUGADORES DE EQUIPO 4
        date.set(1982, Calendar.FEBRUARY, 10);
        Jugador jugador16 = new Jugador(
                "Roberto Guerra",
                date.getTime(),
                65, 135, 354, "Escolta", equipo4);
        jugadorRepository.save(jugador16);

        date.set(1986, Calendar.OCTOBER, 24);
        Jugador jugador17 = new Jugador(
                "Brand Newley",
                date.getTime(),
                22, 40, 50, "Alero", equipo4);
        jugadorRepository.save(jugador17);

        date.set(1975, Calendar.APRIL, 25);
        Jugador jugador18 = new Jugador(
                "Ryan Toolson",
                date.getTime(),
                122, 132, 142, "Escolta", equipo4);
        jugadorRepository.save(jugador18);

        date.set(1983, Calendar.AUGUST, 1);
        Jugador jugador19 = new Jugador(
                "Tomás Bellas",
                date.getTime(),
                21, 50, 345, "Base", equipo4);
        jugadorRepository.save(jugador19);

        date.set(1987, Calendar.MAY, 7);
        Jugador jugador20 = new Jugador(
                "Eulis Báez",
                date.getTime(),
                170, 232, 314, "Ala-Pívot", equipo4);
        jugadorRepository.save(jugador20);

//        JUGADORES DE EQUIPO 5
        date.set(1993, Calendar.AUGUST, 12);
        Jugador jugador21 = new Jugador(
                "Mike James",
                date.getTime(),
                12, 29, 175, "Base", equipo5);
        jugadorRepository.save(jugador21);

        date.set(1989, Calendar.OCTOBER, 21);
        Jugador jugador22 = new Jugador(
                "Mamadou Diop Gaye",
                date.getTime(),
                63, 123, 293, "Ala-Pívot", equipo5);
        jugadorRepository.save(jugador22);

        date.set(1978, Calendar.APRIL, 12);
        Jugador jugador23 = new Jugador(
                "Fabien Causeur",
                date.getTime(),
                272, 372, 521, "Escolta", equipo5);
        jugadorRepository.save(jugador23);

        date.set(1990, Calendar.FEBRUARY, 2);
        Jugador jugador24 = new Jugador(
                "Tornike Shengeliac",
                date.getTime(),
                32, 123, 222, "Ala-Pívot", equipo5);
        jugadorRepository.save(jugador24);

        date.set(1987, Calendar.MAY, 7);
        Jugador jugador25 = new Jugador(
                "Adam Hanga",
                date.getTime(),
                180, 228, 363, "Alero", equipo5);
        jugadorRepository.save(jugador25);


    }
    public void testEntidades(){
        Calendar date = GregorianCalendar.getInstance();

//        SELECTS EJ1
        System.out.println("{Jugador}findByNombreStartingWith: " + jugadorRepository.findByNombreStartingWith("A"));

        System.out.println("{Jugador}findByCanastasGreaterThanEqual: " +
                jugadorRepository.findByCanastasGreaterThanEqual(100));

        System.out.println("{Jugador}findByAsistenciasBetween: " +
                jugadorRepository.findByAsistenciasBetween(170, 230));

        System.out.println("{Jugador}findByPosicion: " +
                jugadorRepository.findByPosicion("Escolta"));

        date.set(1980, Calendar.DECEMBER, 27);
        System.out.println("{Jugador}findByFechaNacimientoBefore: " +
                jugadorRepository.findByFechaNacimientoBefore(date.getTime()));

        date.set(1984, Calendar.AUGUST, 10);
        System.out.println("{Jugador}findByCanastasGreaterThanEqualAndFechaNacimientoBefore: " +
                jugadorRepository.findByCanastasGreaterThanEqualAndFechaNacimientoBefore(120, date.getTime()));

//        SELECTS EJ2
        System.out.println("{Equipo}findByLocalidad: " + equipoRepository.findByLocalidad("Barcelona"));

        System.out.println("{Jugador}findByEquipo_Nombre: " + jugadorRepository.findByEquipo_Nombre("Unicaja"));

        System.out.println("{Jugador}findByEquipoNombreAndPosicion: " +
                jugadorRepository.findByEquipoNombreAndPosicion("Herbalife Gran Canaria", "Base"));

        System.out.println("{Jugador}findFirstByOrderByCanastasDesc: " +
                jugadorRepository.findFirstByOrderByCanastasDesc());

        System.out.println("{Jugador}findFirst5ByOrderByAsistenciasDesc: " +
                jugadorRepository.findFirst5ByOrderByAsistenciasDesc());

        System.out.println("{Jugador}findByEquipoNombreCanastasDesc: " +
                jugadorRepository.findByEquipoNombreCanastasDesc("Laboral Kutxa Baskonia").get(0));
    }
}
