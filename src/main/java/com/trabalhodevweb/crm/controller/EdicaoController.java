package com.trabalhodevweb.crm.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trabalhodevweb.crm.model.Edicao;
import com.trabalhodevweb.crm.model.Evento;
import com.trabalhodevweb.crm.model.Usuario;
import com.trabalhodevweb.crm.model.dto.StoreEdicaoDTO;
import com.trabalhodevweb.crm.repository.EdicaoRepository;
import com.trabalhodevweb.crm.repository.EventoRepository;
import com.trabalhodevweb.crm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/edicao")
public class EdicaoController {
    @Autowired
    private EdicaoRepository edicaoRepository;
    @GetMapping
    public ResponseEntity<?> listar(){
        try{
            List<Edicao> result = edicaoRepository.findAll();
            System.out.println("teste: "+result);
            return ResponseEntity.ok(result.get(0));
        }catch (HttpMessageNotWritableException e){
            System.out.println("causa: "+e.getCause());
            System.out.println("mensagem: "+e.getMessage());
            System.out.println("classe: "+e.getClass());
            return ResponseEntity.badRequest().body("Evento não existe no banco de dados!");
        }

    }

    @GetMapping("/{id}")
    public Optional<Edicao> show(@PathVariable String id){
        Optional<Edicao> result = edicaoRepository.findById(id);
        return result;
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Edicao edicao){
//        Edicao newEdicao = edicaoRepository.findById(id).orElse(null);
//        if(newEdicao != null){
//            newEdicao.setNome(evento.getNome() !=null? evento.getNome() : newEvento.getNome());
//            newEdicao.setDescricao(evento.getDescricao() !=null? evento.getDescricao() : newEvento.getDescricao());
//            newEdicao.setSigla(evento.getSigla() !=null? evento.getSigla() : newEvento.getSigla());
//            eventoRepository.save(newEvento);
//            return ResponseEntity.ok(newEvento);
//        }
//        return ResponseEntity.badRequest().body("Evento não existe no banco de dados!");
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id){
        Edicao newEdicao = edicaoRepository.findById(id).orElse(null);
        if(newEdicao != null){
            edicaoRepository.delete(newEdicao);
            return ResponseEntity.ok("Edicao deletado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Edicao não existe no banco de dados!");
    }





}
