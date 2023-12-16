package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Cargo;
import com.trabalhodevweb.crm.model.Recurso;
import com.trabalhodevweb.crm.model.Usuario;
import com.trabalhodevweb.crm.model.dto.MudarCargoDTO;
import com.trabalhodevweb.crm.model.dto.RegistrarUsuarioComCargoDTO;
import com.trabalhodevweb.crm.repository.RecursoRepository;
import com.trabalhodevweb.crm.repository.UsuarioRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recurso")
public class RecursoController {
    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping
    public Iterable<Recurso> listar(){
        return recursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recurso> show(@PathVariable String id){
        return recursoRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Recurso> store(@RequestBody Recurso recurso) {
        Recurso newrecurso = recursoRepository.save(recurso);
        return ResponseEntity.ok(newrecurso);
    }
}
