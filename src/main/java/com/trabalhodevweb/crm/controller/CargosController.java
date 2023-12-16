package com.trabalhodevweb.crm.controller;

import java.util.List;

import com.trabalhodevweb.crm.model.Cargo;
import com.trabalhodevweb.crm.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargos")
public class CargosController {
    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping
    public List<Cargo> listar(){
        return cargoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cargo store(@RequestBody Cargo cargo){
        return cargoRepository.save(cargo);
    }
}
