package com.natanverdes.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Liga {
//     ---------------------------------------------------------
//     -------------------- PROPIEDADES ------------------------
//     ---------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

//    Creamos una relación ManyToMany con los lig. Esta es una relación débil.
    @OneToMany(mappedBy = "liga")
    private Set<Temporada> temporadas = new HashSet<>();

//     ---------------------------------------------------------
//     -------------------- CONSTRUCTORES ----------------------
//     ---------------------------------------------------------

    public Liga() {
    }

    public Liga(String nombre) {
        this.nombre = nombre;
    }

//     -------------------------------------------------------
//     -------------------- TO SRING -------------------------
//     -------------------------------------------------------

    @Override
    public String toString() {
        return "Liga{" +
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

    public Set<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Set<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}
