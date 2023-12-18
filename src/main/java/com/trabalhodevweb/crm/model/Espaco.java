package com.trabalhodevweb.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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
    @JsonIgnore
    private Set<Recurso> recursos = new HashSet<>();

    @OneToMany(mappedBy = "espaco")
    @JsonIgnore
    private Set<Atividade> atividades;




    public Espaco(){

    }

    public Espaco(String nome, String localizacao, String capacidade) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }

    public void addRecurso(Recurso recurso){
        this.recursos.add(recurso);
        recurso.getEspacos().add(this);
    }

    public void zeraRecursos(){
        this.recursos = new HashSet<>();
    }
}
