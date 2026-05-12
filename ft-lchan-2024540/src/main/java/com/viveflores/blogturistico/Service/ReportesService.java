package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Reportes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportesService {
    List<Reportes> getAllReportes();
    Reportes getReporteById(Integer id);
    Reportes saveReportes(Reportes reportes);
    Reportes updateReportes(Integer id, Reportes reportes);
    void deleteReportes(Integer id);
}
