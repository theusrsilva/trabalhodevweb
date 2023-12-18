package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Atividade;
import com.trabalhodevweb.crm.model.Edicao;
import com.trabalhodevweb.crm.model.Espaco;
import com.trabalhodevweb.crm.model.Usuario;
import com.trabalhodevweb.crm.model.dto.StoreAtividadeDTO;
import com.trabalhodevweb.crm.model.dto.StoreEdicaoDTO;
import com.trabalhodevweb.crm.repository.AtividadeRepository;
import com.trabalhodevweb.crm.repository.EdicaoRepository;
import com.trabalhodevweb.crm.repository.EspacoRepository;
import com.trabalhodevweb.crm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
    @Autowired
    private EdicaoRepository edicaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EspacoRepository espacoRepository;
    @Autowired
    private AtividadeRepository atividadeRepository;



    @GetMapping
    public ResponseEntity<?> listar(){
            List<Atividade> result = atividadeRepository.findAll();
            return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public Optional<Atividade> show(@PathVariable String id){
        Optional<Atividade> result = atividadeRepository.findById(id);
        return result;
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody StoreAtividadeDTO atividade){
        Atividade newAtividade = atividadeRepository.findById(id).orElse(null);
        if(newAtividade != null){
            newAtividade.setNome(atividade.nome() !=null? atividade.nome() : newAtividade.getNome());
            if(atividade.espaco()!= null){
                Espaco espaco = espacoRepository.findById(atividade.espaco()).orElse(null);
                if(espaco !=null){
                    newAtividade.setEspaco(espaco);
                }
            }
            atividadeRepository.save(newAtividade);
            return ResponseEntity.ok(newAtividade);
        }
        return ResponseEntity.badRequest().body("Atividade não existe no banco de dados!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id){
        Atividade atividade = atividadeRepository.findById(id).orElse(null);
        if(atividade != null){
            atividadeRepository.delete(atividade);
            return ResponseEntity.ok("Atividade deletado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Atividade não existe no banco de dados!");
    }


}
