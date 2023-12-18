package com.trabalhodevweb.crm.model.dto;


import java.sql.Date;

public record UpdateEdicaoDTO(String responsavel_id, String ano, Date data_inicial, Date data_final, String cidade, String numero) {
}
