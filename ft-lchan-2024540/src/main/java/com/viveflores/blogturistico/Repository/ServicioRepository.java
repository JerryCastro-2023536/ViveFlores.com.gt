package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<servicio, Integer> {
}
