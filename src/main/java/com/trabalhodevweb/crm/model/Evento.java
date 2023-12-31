package com.trabalhodevweb.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "eventos")
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String sigla;
    private String descricao;

    @OneToMany(mappedBy = "evento")
    private List<Edicao> edicoes;

    public Evento(){

    }

    public Evento(String nome, String sigla, String descricao){
        this.sigla = sigla;
        this.nome = nome;
        this.descricao = descricao;
    }


}
