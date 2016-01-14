package com.natanverdes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Jugador {
//     ---------------------------------------------------------
//     -------------------- PROPIEDADES ------------------------
//     ---------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @JsonIgnore: Lo utilizamos  para que las peticiones REST no respondan dando esta variable.

//    @ManyToOne: Lo usamos para definir una relación ManyToOne, guardando el equipo en la entidad jugador
    @JsonIgnore
    @ManyToOne
    private Equipo equipo;

    private String nombre;

    private Date fechaNacimiento;
    
    private int canastas;

    private int asistencias;

    private int rebotes;

    private String posicion;


//     -----------------------------------------------------------
//     -------------------- CONSTRUCTORES ------------------------
//     -----------------------------------------------------------
    public Jugador(){}
    public Jugador(String nombre, Date fechaNacimiento, int canastas, int asistencias,
                   int rebotes, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.canastas = canastas;
        this.asistencias = asistencias;
        this.rebotes = rebotes;
        this.posicion = posicion;
        this.equipo = equipo;
    }

//     -------------------------------------------------------
//     -------------------- TO SRING -------------------------
//     -------------------------------------------------------

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", canastas=" + canastas +
                ", asistencias=" + asistencias +
                ", rebotes=" + rebotes +
                ", posicion='" + posicion + '\'' +
                ", equipo=" + equipo +
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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCanastas() {
        return canastas;
    }

    public void setCanastas(int canastas) {
        this.canastas = canastas;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
