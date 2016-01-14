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

/**
 * A Equipo.
 */
@Entity
@Table(name = "equipo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToOne    private Estadio estadio;

    @OneToMany(mappedBy = "equipo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Jugador> jugadoress = new HashSet<>();

    @OneToOne    private Entrenador entrenador;

    @ManyToMany(mappedBy = "equiposEsSocios")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Socio> socioss = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "equipo_temporadas",
               joinColumns = @JoinColumn(name="equipos_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="temporadass_id", referencedColumnName="ID"))
    private Set<Temporada> temporadass = new HashSet<>();

    @OneToMany(mappedBy = "equipoLocal")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> partidosJugadosComoLocals = new HashSet<>();

    @OneToMany(mappedBy = "equipoVisitante")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> partidosJugadosComoVisitantes = new HashSet<>();

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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Set<Jugador> getJugadoress() {
        return jugadoress;
    }

    public void setJugadoress(Set<Jugador> jugadors) {
        this.jugadoress = jugadors;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Set<Socio> getSocioss() {
        return socioss;
    }

    public void setSocioss(Set<Socio> socios) {
        this.socioss = socios;
    }

    public Set<Temporada> getTemporadass() {
        return temporadass;
    }

    public void setTemporadass(Set<Temporada> temporadas) {
        this.temporadass = temporadas;
    }

    public Set<Partido> getPartidosJugadosComoLocals() {
        return partidosJugadosComoLocals;
    }

    public void setPartidosJugadosComoLocals(Set<Partido> partidos) {
        this.partidosJugadosComoLocals = partidos;
    }

    public Set<Partido> getPartidosJugadosComoVisitantes() {
        return partidosJugadosComoVisitantes;
    }

    public void setPartidosJugadosComoVisitantes(Set<Partido> partidos) {
        this.partidosJugadosComoVisitantes = partidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipo equipo = (Equipo) o;
        return Objects.equals(id, equipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Equipo{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", localidad='" + localidad + "'" +
            ", fechaCreacion='" + fechaCreacion + "'" +
            '}';
    }
}
