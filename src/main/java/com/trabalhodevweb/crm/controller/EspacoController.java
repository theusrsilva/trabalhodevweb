package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Espaco;
import com.trabalhodevweb.crm.model.Recurso;
import com.trabalhodevweb.crm.model.dto.RegistroEspaçoComRecursoDTO;
import com.trabalhodevweb.crm.repository.EspacoRepository;
import com.trabalhodevweb.crm.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/espaco")
public class EspacoController {
    @Autowired
    private EspacoRepository espacoRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping
    public List<Espaco> listar(){
        return espacoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Espaco> show(@PathVariable String id){
        return espacoRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> store(@RequestBody RegistroEspaçoComRecursoDTO espaco) {
        Espaco newEspaco = new Espaco();
        newEspaco.setNome(espaco.nome());
        newEspaco.setLocalizacao(espaco.localizacao());
        newEspaco.setCapacidade(espaco.capacidade());
        Set<Recurso> recursos = new HashSet<>();
        for (String recursoId : espaco.recursosId()) {
            Recurso recurso = recursoRepository.findById(recursoId).orElse(null);
            if (recurso != null) {
                recursos.add(recurso);
            } else {
                return ResponseEntity.badRequest().body("Recurso com ID " + recursoId + " não encontrado");
            }
        }
        newEspaco.setRecursos(recursos);
        espacoRepository.save(newEspaco);
        return ResponseEntity.ok(newEspaco);
    }
}
