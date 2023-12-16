package com.trabalhodevweb.crm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "recursos")
@Getter
@Setter
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    @ManyToMany(mappedBy = "recursos")
    private Set<Espaco> espacos;

}
