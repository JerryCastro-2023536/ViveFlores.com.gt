package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Reportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportesRepository extends JpaRepository<Reportes, Integer> {
    @Query("SELECT r FROM Reportes r WHERE r.id_usuario = :idUsuario")
    List<Reportes> findByIdUsuario(@Param("idUsuario") Integer idUsuario);
}
