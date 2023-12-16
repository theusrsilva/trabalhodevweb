package com.trabalhodevweb.crm.model.dto;

import java.util.List;

public record RegistroEspaçoComRecursoDTO(String nome, String localizacao, String capacidade, List<String>recursosId) {
}
