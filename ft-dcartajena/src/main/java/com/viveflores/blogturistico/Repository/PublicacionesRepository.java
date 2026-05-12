package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionesRepository extends JpaRepository <Publicaciones, Integer> {
}
