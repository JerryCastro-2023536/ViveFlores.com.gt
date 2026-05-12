package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Reportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportesRepository extends JpaRepository<Reportes, Integer> {
}
