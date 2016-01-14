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
 * A Partido.
 */
@Entity
@Table(name = "partido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "canastas_equipo_local")
    private Integer canastasEquipoLocal;

    @Column(name = "canastas_equipo_visitante")
    private Integer canastasEquipoVisitante;

    @OneToMany(mappedBy = "partido")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JugadorJuegaPartido> jugadorJuegaPartidos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "equipo_local_id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante_id")
    private Equipo equipoVisitante;

    @ManyToOne
    @JoinColumn(name = "temporadas_id")
    private Temporada temporadas;

    @ManyToOne
    @JoinColumn(name = "arbitro_id")
    private Arbitro arbitro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCanastasEquipoLocal() {
        return canastasEquipoLocal;
    }

    public void setCanastasEquipoLocal(Integer canastasEquipoLocal) {
        this.canastasEquipoLocal = canastasEquipoLocal;
    }

    public Integer getCanastasEquipoVisitante() {
        return canastasEquipoVisitante;
    }

    public void setCanastasEquipoVisitante(Integer canastasEquipoVisitante) {
        this.canastasEquipoVisitante = canastasEquipoVisitante;
    }

    public Set<JugadorJuegaPartido> getJugadorJuegaPartidos() {
        return jugadorJuegaPartidos;
    }

    public void setJugadorJuegaPartidos(Set<JugadorJuegaPartido> jugadorJuegaPartidos) {
        this.jugadorJuegaPartidos = jugadorJuegaPartidos;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipo) {
        this.equipoLocal = equipo;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipo) {
        this.equipoVisitante = equipo;
    }

    public Temporada getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Temporada temporada) {
        this.temporadas = temporada;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Partido partido = (Partido) o;
        return Objects.equals(id, partido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Partido{" +
            "id=" + id +
            ", fecha='" + fecha + "'" +
            ", canastasEquipoLocal='" + canastasEquipoLocal + "'" +
            ", canastasEquipoVisitante='" + canastasEquipoVisitante + "'" +
            '}';
    }
}
