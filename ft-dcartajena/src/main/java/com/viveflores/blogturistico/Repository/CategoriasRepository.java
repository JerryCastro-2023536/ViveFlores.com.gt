package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
}
