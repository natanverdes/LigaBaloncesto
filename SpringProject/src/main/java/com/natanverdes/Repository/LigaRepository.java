package com.natanverdes.Repository;

import com.natanverdes.Model.Equipo;
import com.natanverdes.Model.Liga;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LigaRepository extends PagingAndSortingRepository<Liga, Long>{

}
