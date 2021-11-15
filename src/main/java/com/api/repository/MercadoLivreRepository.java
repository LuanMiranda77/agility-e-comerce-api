package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.MercadoLivre;

/**
 * 
 * @author Luan Miranda
 * @Demanada AE-67
 */

@Repository
public interface MercadoLivreRepository extends JpaRepository<MercadoLivre, Long>{

}
