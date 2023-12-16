package com.trabalhodevweb.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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
    @JsonIgnore
    private Set<Espaco> espacos = new HashSet<>();

    public Recurso(){

    }

}
