package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Fotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotosRepository extends JpaRepository<Fotos, Integer> {
}