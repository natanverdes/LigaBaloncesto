package com.natanverdes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Equipo {
//     ---------------------------------------------------------
//     -------------------- PROPIEDADES ------------------------
//     ---------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String localidad;

    private Date fechaCreacion;

//    @JsonIgnore: Utilizaremos la anotación JsonIgnore para que las peticiones REST no devuelvan estos datos
//    @OneToMany: Definimos una relación OneToMany, que es mappedBy (guardada) en la variable "equipo" de Jugador.
//    Esta es una relación débil.
    @JsonIgnore
    @OneToMany(mappedBy = "equipo")
    private Set<Jugador> jugadores = new HashSet<>();

//    Definimos una relación ManyToMany con las Temporadas. Esta es la relación fuerte.
    @JsonIgnore
    @ManyToMany
    private Set<Temporada> temporadasJugadas = new HashSet<>();



//     -----------------------------------------------------------
//     -------------------- CONSTRUCTORES ------------------------
//     -----------------------------------------------------------
    public Equipo() {}
    public Equipo(String nombre, String localidad, Date fechaCreacion) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.fechaCreacion = fechaCreacion;
    }

//     -------------------------------------------------------
//     -------------------- TO SRING -------------------------
//     -------------------------------------------------------

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    //     -------------------------------------------------------------
//     -------------------- GETTERS & SETTERS ----------------------
//     -------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Temporada> getTemporadasJugadas() {
        return temporadasJugadas;
    }

    public void setTemporadasJugadas(Set<Temporada> temporadasJugadas) {
        this.temporadasJugadas = temporadasJugadas;
    }
}
