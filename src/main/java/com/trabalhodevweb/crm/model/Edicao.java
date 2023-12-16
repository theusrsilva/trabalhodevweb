package com.trabalhodevweb.crm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "edicoes")
@Getter
@Setter
public class Edicao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String ano;
    private Date data_inicial;
    private Date data_final;
    private String cidade;
    private String numero;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;



}
