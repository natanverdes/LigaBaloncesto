package com.natanverdes.Controller;

import com.natanverdes.Model.Equipo;
import com.natanverdes.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Equipo> findAll() {
        System.out.println("Petición REST de Equipo findAll");
        List<Equipo> equipos = new ArrayList<>();
        for (Equipo equipo : equipoRepository.findAll()) {
            equipos.add(equipo);
        }
        return equipos;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Equipo create(@RequestBody Equipo equipo) {
        System.out.println("Petición REST de Equipo create");
        return equipoRepository.save(equipo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Equipo updateById(@PathVariable Long id, @RequestBody Equipo equipo) {
        System.out.println("Petición REST de Equipo updateById");
        Equipo equipoBuscado = equipoRepository.findOne(id);

        if(equipoBuscado == null){return new Equipo();}

        return equipoRepository.save(equipo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        System.out.println("Petición REST de Equipo deleteById");
        Equipo equipo = equipoRepository.findOne(id);
        if(equipo != null){equipoRepository.delete(id);}
    }

}
