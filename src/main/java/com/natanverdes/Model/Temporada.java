package com.natanverdes.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Temporada {
//     ---------------------------------------------------------
//     -------------------- PROPIEDADES ------------------------
//     ---------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

//    Definimos una relación ManyToOne con la Liga. Esta es la relación fuerte.
    @ManyToOne
    private Liga liga;

//    Definimos una relación ManyToMany con los Equipos. Esta es la relación débil.
    @ManyToMany(mappedBy = "temporadasJugadas")
    private Set<Equipo> equiposJugando = new HashSet<>();


//     ---------------------------------------------------------
//     -------------------- CONSTRUCTORES ----------------------
//     ---------------------------------------------------------

    public Temporada() {
    }

    public Temporada(String nombre, Liga liga) {
        this.nombre = nombre;
        this.liga = liga;
    }


//     -------------------------------------------------------
//     -------------------- TO SRING -------------------------
//     -------------------------------------------------------

    @Override
    public String toString() {
        return "Temporada{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public Set<Equipo> getEquiposJugando() {
        return equiposJugando;
    }

    public void setEquiposJugando(Set<Equipo> equiposJugando) {
        this.equiposJugando = equiposJugando;
    }
}
