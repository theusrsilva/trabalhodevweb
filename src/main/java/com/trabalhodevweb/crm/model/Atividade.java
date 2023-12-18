package com.trabalhodevweb.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "atividades")
@Getter
@Setter
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "espaco_id", nullable = false)
    private Espaco espaco;

    @ManyToOne
    @JsonIgnore
    private Edicao edicao;

//    @ManyToMany(mappedBy = "atividades")
//    @JsonIgnore
//    private Set<Usuario> usuarios;

    public Atividade(){

    }

    public Atividade(String nome, Edicao edicao, Espaco espaco){
        this.nome = nome;
        this.edicao = edicao;
        this.espaco = espaco;

    }




}
