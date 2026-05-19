package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.SolicitudVendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitudVendedorRepository extends JpaRepository<SolicitudVendedor, Integer> {
    
    @Query("SELECT s FROM SolicitudVendedor s WHERE s.id_usuario = :idUsuario")
    Optional<SolicitudVendedor> findByIdUsuario(@Param("idUsuario") Integer idUsuario);
}
