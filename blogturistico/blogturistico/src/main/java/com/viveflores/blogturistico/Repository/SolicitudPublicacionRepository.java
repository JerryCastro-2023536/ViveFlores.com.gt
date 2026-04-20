package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.SolicitudPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudPublicacionRepository extends JpaRepository<SolicitudPublicacion,Integer> {
}