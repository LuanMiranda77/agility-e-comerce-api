package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Usuario;

//@autor Jadson Feitosa #AE-42

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
