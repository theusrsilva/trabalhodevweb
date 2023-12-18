package com.trabalhodevweb.crm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique=true)
    private String login;
    @Column(unique=true)
    private String email;
    private String nome;
    private String senha;

    private Cargo cargo;

    public Usuario(String login,String nome, String senha, String email, Cargo cargo){
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.cargo = cargo;
        this.nome = nome;
    }

    @ManyToMany
    @JoinTable(
            name = "usuarios_atividades",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "atividade_id")
    )
    private Set<Atividade> atividades;

    @OneToMany(mappedBy = "responsavel")
    private Set<Edicao> edicoes;

    public Usuario() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.cargo == Cargo.ADMINISTRADOR)
        {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_ORG"),new SimpleGrantedAuthority("ROLE_USER"));
        }else if (this.cargo == Cargo.ORGANIZADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_ORG"),new SimpleGrantedAuthority("ROLE_USER"));
        } else return List.of( new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
