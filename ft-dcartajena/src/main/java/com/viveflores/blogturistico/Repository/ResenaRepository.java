package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {
}
