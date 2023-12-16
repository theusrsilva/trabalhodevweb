package com.trabalhodevweb.crm.repository;

import com.trabalhodevweb.crm.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    UserDetails findByLogin(String login);
}
