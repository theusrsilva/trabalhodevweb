package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Espaco;
import com.trabalhodevweb.crm.model.Recurso;
import com.trabalhodevweb.crm.repository.EspacoRepository;
import com.trabalhodevweb.crm.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/espaco")
public class EspacoController {
    @Autowired
    private EspacoRepository espacoRepository;

    @GetMapping
    public List<Espaco> listar(){
        return espacoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Espaco> show(@PathVariable String id){
        return espacoRepository.findById(id);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Espaco> store(@RequestBody Espaco espaco) {
//        Recurso newrecurso = espacoRepository.save(espaco);
//        return ResponseEntity.ok(newrecurso);
//    }
}
