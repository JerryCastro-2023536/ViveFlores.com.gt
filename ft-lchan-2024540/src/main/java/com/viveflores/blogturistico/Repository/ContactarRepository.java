package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Contactar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactarRepository extends JpaRepository<Contactar, Integer> {
}