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
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/recurso")
public class RecursoController {
    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping
    public List<Recurso> listar(){
        return recursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recurso> show(@PathVariable String id){
        return recursoRepository.findById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Recurso recurso){
        Recurso newRecurso = recursoRepository.findById(id).orElse(null);
        if(newRecurso != null){
            newRecurso.setNome(recurso.getNome() !=null? recurso.getNome() : newRecurso.getNome());
            recursoRepository.save(newRecurso);
            return ResponseEntity.ok(newRecurso);
        }
        return ResponseEntity.badRequest().body("Recurso não existe no banco de dados!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id){
        Recurso newRecurso = recursoRepository.findById(id).orElse(null);
        if(newRecurso != null){
            recursoRepository.delete(newRecurso);
            return ResponseEntity.ok("Recurso deletado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Recurso não existe no banco de dados!");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Recurso> store(@RequestBody Recurso recurso) {
        Recurso newrecurso = recursoRepository.save(recurso);
        return ResponseEntity.ok(newrecurso);
    }
}
