package com.trabalhodevweb.crm.controller;


import com.trabalhodevweb.crm.model.*;
import com.trabalhodevweb.crm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seeder")
public class SeederController {

    @Autowired
    RecursoRepository recursoRepository;
    @Autowired
    EspacoRepository espacoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    EdicaoRepository edicaoRepository;
    @Autowired
    AtividadeRepository atividadeRepository;

    @GetMapping
    public ResponseEntity<?> gerar(){

        Usuario usuario = new Usuario("usuarioTeste", "Usuario Teste", new BCryptPasswordEncoder().encode("123456789"), "usuarioteste@teste.com", Cargo.ADMINISTRADOR);
        Usuario newUsuario = usuarioRepository.save(usuario);

        Recurso recurso = new Recurso("Recurso Teste 1");
        Recurso newRecurso = recursoRepository.save(recurso);

        Espaco espaco = new Espaco("Espaco Teste 1", "Localizacao Teste 1", "Capacidade teste 1", newRecurso);
        Espaco newEspaco = espacoRepository.save(espaco);

        Evento evento = new Evento("evento teste 1", "ET1", "descricao evento teste 1");
        Evento newEvento = eventoRepository.save(evento);

        Edicao edicao = new Edicao("2024", "2023-12-31","2024-01-01", "Cidade teste", "1", newUsuario, newEvento);
        Edicao newEdicao = edicaoRepository.save(edicao);

        Atividade atividade = new Atividade("Atividade teste", newEdicao, newEspaco);
        Atividade newAtividade = atividadeRepository.save(atividade);


        return ResponseEntity.ok().build();
    }


}
