package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

}