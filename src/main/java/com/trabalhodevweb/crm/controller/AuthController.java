package com.trabalhodevweb.crm.controller;

import com.trabalhodevweb.crm.model.Cargo;
import com.trabalhodevweb.crm.model.Usuario;
import com.trabalhodevweb.crm.model.dto.AuthenticationDTO;
import com.trabalhodevweb.crm.model.dto.RegisterDTO;
import com.trabalhodevweb.crm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String senhaEncriptada = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.login(), data.nome(), senhaEncriptada, data.email(), Cargo.USUARIO);

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();

    }
}
