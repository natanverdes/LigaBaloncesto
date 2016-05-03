package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Player entity.
 */
public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("select player from Player player where player.baskets >=:baskets")
    Page<Player> topPlayers(@Param("baskets") Integer baskets, Pageable pageable);

    @Query("select player from Player player where player.asistencias >=:asistencias")
    Page<Player> topPlayersAsistencias(@Param("asistencias") Integer asistencias, Pageable pageable);

}
