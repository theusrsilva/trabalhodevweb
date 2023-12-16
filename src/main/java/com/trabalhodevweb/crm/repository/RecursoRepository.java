package com.trabalhodevweb.crm.repository;

import com.trabalhodevweb.crm.model.Recurso;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso,String> {
}

