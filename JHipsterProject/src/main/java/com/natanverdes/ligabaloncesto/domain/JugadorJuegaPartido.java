package com.natanverdes.ligabaloncesto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A JugadorJuegaPartido.
 */
@Entity
@Table(name = "jugador_juega_partido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JugadorJuegaPartido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "canastas")
    private Integer canastas;

    @Column(name = "asistencias")
    private Integer asistencias;

    @Column(name = "rebotes")
    private Integer rebotes;

    @Column(name = "faltas")
    private Integer faltas;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCanastas() {
        return canastas;
    }

    public void setCanastas(Integer canastas) {
        this.canastas = canastas;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public Integer getRebotes() {
        return rebotes;
    }

    public void setRebotes(Integer rebotes) {
        this.rebotes = rebotes;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JugadorJuegaPartido jugadorJuegaPartido = (JugadorJuegaPartido) o;
        return Objects.equals(id, jugadorJuegaPartido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JugadorJuegaPartido{" +
            "id=" + id +
            ", canastas='" + canastas + "'" +
            ", asistencias='" + asistencias + "'" +
            ", rebotes='" + rebotes + "'" +
            ", faltas='" + faltas + "'" +
            '}';
    }
}
