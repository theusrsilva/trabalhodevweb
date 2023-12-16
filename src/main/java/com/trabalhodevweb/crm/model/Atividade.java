package com.trabalhodevweb.crm.model;

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
    private String localizacao;
    private String capacidade;
    @ManyToOne
    @JoinColumn(name = "espaco_id", nullable = false)
    private Espaco espaco;
    @ManyToMany(mappedBy = "atividades")
    private Set<Usuario> usuarios;



}