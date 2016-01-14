package com.natanverdes.Service;

import com.natanverdes.Repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TemporadaService {
    @Autowired
    private TemporadaRepository temporadaRepository;

}
