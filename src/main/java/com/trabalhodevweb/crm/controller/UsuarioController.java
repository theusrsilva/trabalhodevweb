package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Cargo;
import com.trabalhodevweb.crm.model.Usuario;
import com.trabalhodevweb.crm.model.dto.MudarCargoDTO;
import com.trabalhodevweb.crm.model.dto.RegistrarUsuarioComCargoDTO;
import com.trabalhodevweb.crm.repository.UsuarioRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Iterable<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> show(@PathVariable String id){
        return usuarioRepository.findById(id);
    }

    @PostMapping("/{id}/cargo")
    public ResponseEntity<Object> alterarCargo(@PathVariable String id, @RequestBody MudarCargoDTO cargo){
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user== null)return ResponseEntity.notFound().build();

        if(EnumUtils.isValidEnum(Cargo.class, cargo.cargo())){
            Cargo permissao = Cargo.valueOf(cargo.cargo());
            user.setCargo(permissao);
            usuarioRepository.save(user);
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.badRequest().body("Cargo inválido");
        }

    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Usuario store(@RequestBody Usuario usuario){
//        return usuarioRepository.save(usuario);
//    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> store(@RequestBody RegistrarUsuarioComCargoDTO data) {

        if (EnumUtils.isValidEnum(Cargo.class, data.cargo())) {
            Cargo permissao = Cargo.valueOf(data.cargo());
            String senhaEncriptada = new BCryptPasswordEncoder().encode(data.senha());
            Usuario usuario = new Usuario(data.login(), data.nome(), senhaEncriptada, data.email(), permissao);
            Usuario savedUser = usuarioRepository.save(usuario);
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
