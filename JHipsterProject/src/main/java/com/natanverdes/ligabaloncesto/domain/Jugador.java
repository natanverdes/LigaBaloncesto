package com.natanverdes.ligabaloncesto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.natanverdes.ligabaloncesto.domain.enumeration.Posicion;

/**
 * A Jugador.
 */
@Entity
@Table(name = "jugador")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Jugador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "posicion")
    private Posicion posicion;

    @Column(name = "canastas_totales")
    private Integer canastasTotales;

    @Column(name = "asistencias_totales")
    private Integer asistenciasTotales;

    @Column(name = "rebotes_totales")
    private Integer rebotesTotales;

    @Column(name = "faltas_totales")
    private Integer faltasTotales;

    @OneToMany(mappedBy = "jugador")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JugadorJuegaPartido> partidosJugadoss = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Integer getCanastasTotales() {
        return canastasTotales;
    }

    public void setCanastasTotales(Integer canastasTotales) {
        this.canastasTotales = canastasTotales;
    }

    public Integer getAsistenciasTotales() {
        return asistenciasTotales;
    }

    public void setAsistenciasTotales(Integer asistenciasTotales) {
        this.asistenciasTotales = asistenciasTotales;
    }

    public Integer getRebotesTotales() {
        return rebotesTotales;
    }

    public void setRebotesTotales(Integer rebotesTotales) {
        this.rebotesTotales = rebotesTotales;
    }

    public Integer getFaltasTotales() {
        return faltasTotales;
    }

    public void setFaltasTotales(Integer faltasTotales) {
        this.faltasTotales = faltasTotales;
    }

    public Set<JugadorJuegaPartido> getPartidosJugadoss() {
        return partidosJugadoss;
    }

    public void setPartidosJugadoss(Set<JugadorJuegaPartido> jugadorJuegaPartidos) {
        this.partidosJugadoss = jugadorJuegaPartidos;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Jugador jugador = (Jugador) o;
        return Objects.equals(id, jugador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Jugador{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", fechaNacimiento='" + fechaNacimiento + "'" +
            ", posicion='" + posicion + "'" +
            ", canastasTotales='" + canastasTotales + "'" +
            ", asistenciasTotales='" + asistenciasTotales + "'" +
            ", rebotesTotales='" + rebotesTotales + "'" +
            ", faltasTotales='" + faltasTotales + "'" +
            '}';
    }
}
