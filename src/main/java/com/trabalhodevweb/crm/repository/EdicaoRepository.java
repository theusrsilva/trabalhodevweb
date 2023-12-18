package com.trabalhodevweb.crm.repository;

import com.trabalhodevweb.crm.model.Edicao;
import com.trabalhodevweb.crm.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdicaoRepository extends JpaRepository<Edicao,String> {
}

