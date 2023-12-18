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
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public ResponseEntity<?> listar(){
            List<Edicao> result = edicaoRepository.findAll();
            System.out.println("teste: "+result);
            return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public Optional<Edicao> show(@PathVariable String id){
        Optional<Edicao> result = edicaoRepository.findById(id);
        return result;
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody StoreEdicaoDTO edicao){
        Edicao newEdicao = edicaoRepository.findById(id).orElse(null);
        if(newEdicao != null){
            newEdicao.setCidade(edicao.cidade() !=null? edicao.cidade() : newEdicao.getCidade());
            newEdicao.setAno(edicao.ano() !=null? edicao.ano() : newEdicao.getAno());
            newEdicao.setData_inicial(edicao.data_inicial() !=null? edicao.data_inicial() : newEdicao.getData_inicial());
            newEdicao.setData_final(edicao.data_final() !=null? edicao.data_final() : newEdicao.getData_final());
            newEdicao.setNumero(edicao.numero() !=null? edicao.numero() : newEdicao.getNumero());
            if(edicao.responsavel_id()!= null){
                Usuario usuario = usuarioRepository.findById(id).orElse(null);
                if(usuario !=null){
                    newEdicao.setResponsavel(usuario);
                }
            }
            edicaoRepository.save(newEdicao);
            return ResponseEntity.ok(newEdicao);
        }
        return ResponseEntity.badRequest().body("Edicao não existe no banco de dados!");
    }
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
