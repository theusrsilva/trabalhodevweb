package com.trabalhodevweb.crm.repository;

import com.trabalhodevweb.crm.model.Atividade;
import com.trabalhodevweb.crm.model.Edicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,String> {
}

