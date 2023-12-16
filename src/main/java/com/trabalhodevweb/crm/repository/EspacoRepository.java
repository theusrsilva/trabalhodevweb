package com.trabalhodevweb.crm.repository;

import com.trabalhodevweb.crm.model.Espaco;
import com.trabalhodevweb.crm.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco,String> {
}

