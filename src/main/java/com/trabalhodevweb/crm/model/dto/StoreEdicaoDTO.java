package com.trabalhodevweb.crm.model.dto;


import java.sql.Date;

public record StoreEdicaoDTO(String responsavel_id, String ano, Date data_inicial, Date data_final, String cidade, String numero) {
}
