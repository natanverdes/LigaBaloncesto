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
 * A Temporada.
 */
@Entity
@Table(name = "temporada")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Temporada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ano")
    private LocalDate ano;

    @ManyToMany(mappedBy = "temporadass")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Equipo> equiposs = new HashSet<>();

    @ManyToMany(mappedBy = "temporadas")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> partidoss = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
        this.ano = ano;
    }

    public Set<Equipo> getEquiposs() {
        return equiposs;
    }

    public void setEquiposs(Set<Equipo> equipos) {
        this.equiposs = equipos;
    }

    public Set<Partido> getPartidoss() {
        return partidoss;
    }

    public void setPartidoss(Set<Partido> partidos) {
        this.partidoss = partidos;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Temporada temporada = (Temporada) o;
        return Objects.equals(id, temporada.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Temporada{" +
            "id=" + id +
            ", ano='" + ano + "'" +
            '}';
    }
}
