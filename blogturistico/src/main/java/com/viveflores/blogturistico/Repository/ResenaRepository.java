package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    
    @Query("SELECT r FROM Resena r WHERE r.id_publicacion = :idPublicacion")
    List<Resena> findByIdPublicacion(@Param("idPublicacion") Integer idPublicacion);
}
