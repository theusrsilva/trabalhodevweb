package com.trabalhodevweb.crm.model;

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


}
