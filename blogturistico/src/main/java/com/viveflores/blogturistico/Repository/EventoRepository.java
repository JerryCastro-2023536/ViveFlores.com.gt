package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Integer> {
}
