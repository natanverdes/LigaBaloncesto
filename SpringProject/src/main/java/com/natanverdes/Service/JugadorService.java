package com.natanverdes.Service;

import com.natanverdes.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JugadorService {
    @Autowired
    JugadorRepository jugadorRepository;

}
