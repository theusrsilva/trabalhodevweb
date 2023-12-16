package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Evento;
import com.trabalhodevweb.crm.model.Recurso;
import com.trabalhodevweb.crm.repository.EventoRepository;
import com.trabalhodevweb.crm.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public List<Evento> listar(){
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Evento> show(@PathVariable String id){
        return eventoRepository.findById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Evento evento){
        Evento newEvento = eventoRepository.findById(id).orElse(null);
        if(newEvento != null){
            newEvento.setNome(evento.getNome() !=null? evento.getNome() : newEvento.getNome());
            newEvento.setDescricao(evento.getDescricao() !=null? evento.getDescricao() : newEvento.getDescricao());
            newEvento.setSigla(evento.getSigla() !=null? evento.getSigla() : newEvento.getSigla());
            eventoRepository.save(newEvento);
            return ResponseEntity.ok(newEvento);
        }
        return ResponseEntity.badRequest().body("Evento não existe no banco de dados!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id){
        Evento newEvento = eventoRepository.findById(id).orElse(null);
        if(newEvento != null){
            eventoRepository.delete(newEvento);
            return ResponseEntity.ok("Evento deletado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Evento não existe no banco de dados!");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Evento> store(@RequestBody Evento evento) {
        Evento newevento = eventoRepository.save(evento);
        return ResponseEntity.ok(newevento);
    }
}
