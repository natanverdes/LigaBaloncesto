package com.natanverdes.Model;

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

    private String nombre;

    private Date fechaNacimiento;
    
    private int numeroCanastasTotales;

    private int numeroAsistenciasTotales;

    private int numeroRebotesTotales;

    private String posicionEnElCampoPorDefecto;


//     -----------------------------------------------------------
//     -------------------- CONSTRUCTORES ------------------------
//     -----------------------------------------------------------
    public Jugador(){}
    public Jugador(String nombre, Date fechaNacimiento, int numeroCanastasTotales, int numeroAsistenciasTotales, int numeroRebotesTotales, String posicionEnElCampoPorDefecto) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroCanastasTotales = numeroCanastasTotales;
        this.numeroAsistenciasTotales = numeroAsistenciasTotales;
        this.numeroRebotesTotales = numeroRebotesTotales;
        this.posicionEnElCampoPorDefecto = posicionEnElCampoPorDefecto;
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
                ", numeroCanastasTotales=" + numeroCanastasTotales +
                ", numeroAsistenciasTotales=" + numeroAsistenciasTotales +
                ", numeroRebotesTotales=" + numeroRebotesTotales +
                ", posicionEnElCampoPorDefecto='" + posicionEnElCampoPorDefecto + '\'' +
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getNumeroCanastasTotales() {
        return numeroCanastasTotales;
    }

    public void setNumeroCanastasTotales(int numeroCanastasTotales) {
        this.numeroCanastasTotales = numeroCanastasTotales;
    }

    public int getNumeroAsistenciasTotales() {
        return numeroAsistenciasTotales;
    }

    public void setNumeroAsistenciasTotales(int numeroAsistenciasTotales) {
        this.numeroAsistenciasTotales = numeroAsistenciasTotales;
    }

    public int getNumeroRebotesTotales() {
        return numeroRebotesTotales;
    }

    public void setNumeroRebotesTotales(int numeroRebotesTotales) {
        this.numeroRebotesTotales = numeroRebotesTotales;
    }

    public String getPosicionEnElCampoPorDefecto() {
        return posicionEnElCampoPorDefecto;
    }

    public void setPosicionEnElCampoPorDefecto(String posicionEnElCampoPorDefecto) {
        this.posicionEnElCampoPorDefecto = posicionEnElCampoPorDefecto;
    }
}
