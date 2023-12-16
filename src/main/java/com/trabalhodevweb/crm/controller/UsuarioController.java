package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Usuario;
import com.trabalhodevweb.crm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Iterable<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario store(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
