package com.trabalhodevweb.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trabalhodevweb.crm.model.dto.StoreEdicaoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JsonIgnore
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    @JsonIgnore
    private Usuario responsavel;

    @OneToMany(mappedBy = "edicao")
    private List<Atividade> atividades;

    public Edicao(){
    }
    public Edicao(StoreEdicaoDTO storeEdicaoDTO, Usuario usuario, Evento evento){
        this.ano = storeEdicaoDTO.ano();
        this.data_final = storeEdicaoDTO.data_final();
        this.data_inicial = storeEdicaoDTO.data_inicial();
        this.cidade = storeEdicaoDTO.cidade();
        this.numero = storeEdicaoDTO.numero();
        this.responsavel = usuario;
        this.evento = evento;
    }

    public Edicao(String ano, String data_final,String data_inicial, String cidade, String numero, Usuario usuario, Evento evento){
        try{
            this.ano = ano;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            this.data_final = formato.parse(data_final);
            this.data_inicial = formato.parse(data_inicial);
            this.cidade = cidade;
            this.numero = numero;
            this.responsavel = usuario;
            this.evento = evento;
        }catch (ParseException e){
            System.out.println("data em formato errado");
        }

    }

}
