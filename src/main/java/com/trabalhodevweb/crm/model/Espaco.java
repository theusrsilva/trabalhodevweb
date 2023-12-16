package com.trabalhodevweb.crm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "espacos")
@Getter
@Setter
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String localizacao;
    private String capacidade;
    @ManyToMany
    @JoinTable(
            name = "espacos_recursos",
            joinColumns = @JoinColumn(name = "espaco_id"),
            inverseJoinColumns = @JoinColumn(name = "recurso_id")
    )
    private Set<Recurso> recursos;
    @OneToMany(mappedBy = "espaco")
    private Set<Atividade> atividades;
}
